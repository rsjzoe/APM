package org.acme.departement.domain.port.out;

import java.util.List;

import org.acme.departement.domain.Departement;

public interface DepartementRepository {
    List<Departement> getListDepartement();

    Departement findById(int id);
}
