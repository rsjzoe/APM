package org.acme.role.domain.port.out;

import java.util.List;

import org.acme.role.domain.model.Service;

public interface ServiceRepository {
    public List<Service> findAll();
}
