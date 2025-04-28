package org.acme.departement.infra.database;

import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.domain.port.out.DepartementRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DepartementEntityRepository implements DepartementRepository {
    @Transactional
    @Override
    public List<Departement> getListDepartement() {
        List<DepartementEntity> departements = DepartementEntity.listAll();
        return departements.stream()
                .map(departement -> departement.toDepartement())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Departement findById(Long id) throws DepartementNotFoundException {
        DepartementEntity departements = DepartementEntity.findById(id);
        if (departements == null)
            throw new DepartementNotFoundException();
        return departements.toDepartement();
    }

    @Override
    public Departement creaDepartement(String name) {
        DepartementEntity departement = new DepartementEntity();
        departement.setName(name);
        departement.persist();
        return departement.toDepartement();
    }

    @Override
    public Departement findByName(String name) throws DepartementNotFoundException {
        DepartementEntity departement = DepartementEntity.find("name", name).firstResult();
        if (departement == null)
            throw new DepartementNotFoundException();
        return departement.toDepartement();
    }
}
