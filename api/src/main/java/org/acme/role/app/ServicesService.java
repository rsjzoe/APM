package org.acme.role.app;

import java.util.List;

import org.acme.role.domain.exception.ConflitServiceException;
import org.acme.role.domain.exception.ServiceNotFoundException;
import org.acme.role.domain.model.Service;
import org.acme.role.domain.port.out.ServiceRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServicesService {
    @Inject
    ServiceRepository serviceRepository;

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service create(String name) throws ConflitServiceException {
        try {
            findByName(name);
            throw new ConflitServiceException(name);
        } catch (ServiceNotFoundException e) {
            return serviceRepository.create(name);
        }
    }

    public Service findByName(String name) throws ServiceNotFoundException {
        return serviceRepository.findByName(name);
    }
}
