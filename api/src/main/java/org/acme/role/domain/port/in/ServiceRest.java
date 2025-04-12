package org.acme.role.domain.port.in;

import java.util.List;

import org.acme.role.domain.model.Service;

public interface ServiceRest {
    public List<Service> findAll();

}
