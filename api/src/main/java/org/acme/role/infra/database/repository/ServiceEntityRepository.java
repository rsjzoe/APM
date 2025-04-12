package org.acme.role.infra.database.repository;

import java.util.List;

import org.acme.role.domain.model.Service;
import org.acme.role.domain.port.out.ServiceRepository;
import org.acme.role.infra.database.entity.ServiceEntity;

public class ServiceEntityRepository implements ServiceRepository {

    @Override
    public List<Service> findAll() {
        List<ServiceEntity> serviceEntities = ServiceEntity.listAll();
        return serviceEntities.stream()
                .map(ServiceEntity::toService)
                .toList();
    }

}
