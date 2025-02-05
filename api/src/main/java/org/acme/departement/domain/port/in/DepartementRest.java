package org.acme.departement.domain.port.in;

import java.util.List;

import org.acme.departement.domain.Departement;

public interface DepartementRest {

    List<Departement> listDepartement();

    Departement findByDepartementId(int id);
}
