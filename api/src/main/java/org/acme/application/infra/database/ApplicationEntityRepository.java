package org.acme.application.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;

public class ApplicationEntityRepository implements ApplicationRepository {

    @Override
    public List<ApplicationOutput> listAll() {
        List<ApplicationEntity> data = ApplicationEntity.list("isDeleted", false);
        return data.stream()
                .map(entity -> (entity).toApplicationOutput())
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationOutput findById(Long id) throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput create(CreateApplicationRepositoryInput newApplication) {
        ApplicationEntity data = new ApplicationEntity(newApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput update(Long id, UpdateApplicationRepositoryInput updateApplication)
            throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        data.updateData(updateApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput delete(Long id) throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        data.setDeleted(true);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public List<ApplicationOutput> deletedApplication() {
        List<ApplicationEntity> data = ApplicationEntity.list("isDeleted", true);
        return data.stream()
                .map(entity -> (entity).toApplicationOutput())
                .collect(Collectors.toList());
    }

}
