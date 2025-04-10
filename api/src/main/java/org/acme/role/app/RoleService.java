package org.acme.role.app;

import java.util.List;

import org.acme.role.domain.input.CreateRole;
import org.acme.role.domain.model.Role;
import org.acme.role.domain.port.out.RoleRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoleService {
    @Inject
    RoleRepository roleRepository;

    public Role createRole(CreateRole role) {
        return roleRepository.createRole(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
