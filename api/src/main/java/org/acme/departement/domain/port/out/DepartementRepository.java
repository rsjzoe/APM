package org.acme.departement.domain.port.out;

import java.util.List;

import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;

public interface DepartementRepository {
    List<Departement> getListDepartement();

    Departement findById(Long id) throws DepartementNotFoundException;
}
