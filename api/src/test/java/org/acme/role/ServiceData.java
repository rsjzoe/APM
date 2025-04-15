package org.acme.role;

import java.util.List;

import org.acme.role.app.ServicesService;
import org.acme.role.domain.exception.ConflitServiceException;
import org.acme.role.domain.exception.ServiceNotFoundException;
import org.acme.role.domain.model.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ApplicationScoped
@Getter
public class ServiceData {
    @Inject
    ServicesService servicesService;

    private Service application;
    private Service admin;
    private Service roles;
    private Service classification;
    private Service category;
    private Service performance;
    private Service corbeille;
    private List<Service> services;

    public void setup() {
        application = create("application");
        admin = create("admin");
        roles = create("roles");
        classification = create("classification");
        category = create("category");
        performance = create("performance");
        corbeille = create("corbeille");
        services = List.of(application, admin, roles, classification, category, performance, corbeille);
    }

    private Service create(String name) {
        try {
            return servicesService.create(name);
        } catch (ConflitServiceException e) {
            try {
                return servicesService.findByName(name);
            } catch (ServiceNotFoundException e1) {
                return null;
            }
        }
    }
}
