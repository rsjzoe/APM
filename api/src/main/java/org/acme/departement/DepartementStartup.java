package org.acme.departement;

import org.acme.departement.app.DepartementService;
import org.acme.departement.domain.exception.ConflitDepartementException;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DepartementStartup {
    @Inject
    DepartementService departementService;

    @Startup
    @Transactional
    public void init() {
        createDepartement("DSI");
        createDepartement("DT");
        createDepartement("OMM");
        createDepartement("DSMIC");
        createDepartement("DCE");
        createDepartement("DRH");
        createDepartement("DF");
    }

    void createDepartement(String name) {
        try {
            departementService.createDepartement(name);
        } catch (ConflitDepartementException e) {
        }
    }
}
