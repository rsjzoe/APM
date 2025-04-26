package org.acme.user;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.exception.UserCreatedException;
import org.acme.auth.domain.exception.UserExistedException;
import org.acme.auth.domain.input.Register;
import org.acme.role.app.RoleService;
import org.acme.role.app.ServicesService;
import org.acme.role.domain.model.input.CreatePermission;
import org.acme.role.domain.model.input.CreateRole;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserCreateStartup {
    @Inject
    RoleService roleService;

    @Inject
    AuthService authService;

    @Inject
    ServicesService servicesService;

    @Startup
    @Transactional
    void init() {
        initServices();
        var servicesData = servicesService.getAllServices();
        var superAdminPermissions = servicesData.stream()
                .map(service -> new CreatePermission(true, true, true, true, service.getId()))
                .toList();

        var superAdminRole = new CreateRole("superadminapm", superAdminPermissions);

        try {
            roleService.createRole(superAdminRole);
            System.out.println("ROLE superadminapm CREATED");
        } catch (Exception e) {
            // System.out.println(e);
        }

        try {
            authService.register(new Register("superadmin apm", "superadminapm", "DSI", "superadminapm"));
            System.out.println("USER superadminapm CREATED");
        } catch (UserExistedException | UserCreatedException e) {
            // System.out.println(e);
            // System.out.println("ERROR CREATE USER");
        }
    }

    void initServices() {
        createService("application");
        createService("admin");
        createService("classification");
        createService("roles");
        createService("category");
        createService("performance");
        createService("corbeille");
        createService("documentation");
    }

    void createService(String name) {
        try {
            servicesService.create(name);
        } catch (Exception e) {
            // Handle exception
        }
    }
}
