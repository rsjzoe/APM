package org.acme.documentation.app;

import java.io.IOException;
import java.util.List;

import org.acme.application.app.service.ApplicationHistoryService;
import org.acme.application.app.service.ApplicationService;
import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationHistoryService;
import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.input.CreateDocumentation;
import org.acme.documentation.domain.input.CreateDocumentationFile;
import org.acme.documentation.domain.ports.out.DocumentationRepository;
import org.acme.storage.FileNotFound;
import org.acme.storage.Storage;
import org.acme.storage.StorageFile;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DocumentationService {
    @Inject
    Storage storage;

    @Inject
    DocumentationRepository documentationRepository;

    @Inject
    ApplicationService applicationService;

    @Inject
    ApplicationHistoryService applicationHistoryService;

    @Transactional
    public List<Documentation> findDocumentationByAppId(Long id) {
        return documentationRepository.findDocumentationByAppId(id);
    }

    @Transactional
    public Documentation createDocumentation(CreateDocumentationFile input, String token, Boolean createHistory)
            throws IOException, FileNotFound, ApplicationNotFoundException, VerificationTokenException,
            UserNotFoundException {
        StorageFile storageFile = storage.save(input.getFileInput());
        CreateDocumentation createDocumentation = new CreateDocumentation(storageFile.getName(),
                storageFile.getFilename(), storageFile.getUrl(), input.getType(), input.getApplicationId());
        var created = documentationRepository.createDocumentation(createDocumentation);

        var app = applicationService.findById(input.getApplicationId());
        if (createHistory) {
            var createAppHistory = new CreateApplicationHistoryService(app, token,
                    "Ajout de la documentation : '" + created.getName() + "'' - " + created.getType());
            applicationHistoryService.create(createAppHistory);
        }

        return created;
    }

    @Transactional
    public Documentation deleteDocumentation(String filename, String token)
            throws FileNotFound, ApplicationNotFoundException, VerificationTokenException, UserNotFoundException {
        storage.delete(filename);
        var deleted = documentationRepository.deleteDocumentation(filename);

        var app = applicationService.findById(deleted.getApplicationId());
        var createHistory = new CreateApplicationHistoryService(app, token,
                "Suppression de la documentation : '" + deleted.getName() + "' - " + deleted.getType());
        applicationHistoryService.create(createHistory);

        return deleted;
    }

    public byte[] getBytes(String filename) throws FileNotFound, IOException {
        return storage.getBytes(filename);
    }

    public StorageFile getDocumentation(String filename) throws FileNotFound, IOException {
        return storage.get(filename);
    }

}
