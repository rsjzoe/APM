package org.acme.documentation.app;

import java.io.IOException;
import java.util.List;

import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.input.CreateDocumentation;
import org.acme.documentation.domain.input.CreateDocumentationFile;
import org.acme.documentation.domain.ports.out.DocumentationRepository;
import org.acme.storage.FileNotFound;
import org.acme.storage.Storage;
import org.acme.storage.StorageFile;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DocumentationService {
    @Inject
    Storage storage;

    @Inject
    DocumentationRepository documentationRepository;

    @Transactional
    public List<Documentation> findDocumentationByAppId(Long id) {
        return documentationRepository.findDocumentationByAppId(id);
    }

    @Transactional
    public Documentation createDocumentation(CreateDocumentationFile input)
            throws IOException, FileNotFound {
        StorageFile storageFile = storage.save(input.getFileInput());
        CreateDocumentation createDocumentation = new CreateDocumentation(storageFile.getName(),
                storageFile.getFilename(), storageFile.getUrl(), input.getType(), input.getApplicationId());
        var created = documentationRepository.createDocumentation(createDocumentation);
        return created;
    }

    @Transactional
    public Documentation deleteDocumentation(String filename) throws FileNotFound {
        storage.delete(filename);
        return documentationRepository.deleteDocumentation(filename);
    }

    public byte[] getBytes(String filename) throws FileNotFound, IOException {
        return storage.getBytes(filename);
    }

    public StorageFile getDocumentation(String filename) throws FileNotFound, IOException {
        return storage.get(filename);
    }

}
