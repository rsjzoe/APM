package org.acme.role.app;

import java.util.List;

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
}
