package org.acme.role.infra.database.repository;

import java.util.List;

import org.acme.role.domain.exception.ServiceNotFoundException;
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

    @Override
    public Service create(String name) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(name);
        serviceEntity.persist();
        return serviceEntity.toService();
    }

    @Override
    public Service findByName(String name) throws ServiceNotFoundException {
        ServiceEntity serviceEntity = ServiceEntity.find("name", name).firstResult();
        if (serviceEntity == null) {
            throw new ServiceNotFoundException("Service not found with name: " + name);
        }
        return serviceEntity.toService();
    }

}
