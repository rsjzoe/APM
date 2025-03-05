package org.acme.application.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;

public class ApplicationEntityRepository implements ApplicationRepository {

    @Override
    public List<ApplicationOutput> listAll() {
        List<ApplicationEntity> data = ApplicationEntity.listAll();
        return data.stream()
                .map(entity -> (entity).toApplicationOutput())
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationOutput findById(Long id) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null)
            return null;
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput create(CreateApplicationRepositoryInput newApplication) {
        ApplicationEntity data = new ApplicationEntity(newApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput update(Long id, UpdateApplicationRepositoryInput updateApplication) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null)
            return null;
        data.updateData(updateApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput delete(Long id) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null)
            return null;
        // Supprimer les historiques liés
        ApplicationHistoryEntity.delete("applicationEntity = ?1", data);
        data.delete();
        return data.toApplicationOutput();
    }

}
