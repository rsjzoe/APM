package org.acme.application.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.application.domain.model.Application;
import org.acme.application.domain.model.input.CreateApplicationInput;
import org.acme.application.domain.model.input.UpdateApplicationInput;
import org.acme.application.domain.port.out.ApplicationRepository;

public class ApplicationEntityRepository implements ApplicationRepository {

    @Override
    public List<Application> listAll() {
        List<ApplicationEntity> data = ApplicationEntity.listAll();
        return data.stream()
                .map(entity -> (entity).toApplication())
                .collect(Collectors.toList());
    }

    @Override
    public Application findById(Long id) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if(data == null) return null;
        return data.toApplication();
    }

    @Override
    public Application create(CreateApplicationInput newApplication) {
        ApplicationEntity data = new ApplicationEntity(newApplication);
        data.persist();
        return data.toApplication();
    }

    @Override
    public Application update(Long id, UpdateApplicationInput updateApplication) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if(data == null) return null;
        data.updateData(updateApplication);
        data.persist();
        return data.toApplication();
    }

    @Override
    public Application delete(Long id) {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if(data == null) return null;
        // Supprimer les historiques liés
        ApplicationHistoryEntity.delete("applicationEntity = ?1", data);
        data.delete();
        return data.toApplication();
    }

}
