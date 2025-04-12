package org.acme.role.infra.controller;

import java.util.List;

import org.acme.role.app.ServicesService;
import org.acme.role.domain.model.Service;
import org.acme.role.domain.port.in.ServiceRest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("services")
public class ServiceController implements ServiceRest {
    @Inject
    ServicesService servicesService;

    @Override
    @GET
    @Transactional
    public List<Service> findAll() {
        return servicesService.getAllServices();
    }

}
