package org.acme.departement.infra.out;

import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.domain.port.out.DepartementRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DepartementRepositoryImpl implements DepartementRepository {
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
}
