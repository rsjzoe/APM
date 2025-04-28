package org.acme.departement.app;

import java.util.List;

import org.acme.departement.domain.Departement;
import org.acme.departement.domain.exception.ConflitDepartementException;
import org.acme.departement.domain.exception.DepartementNotFoundException;
import org.acme.departement.domain.port.out.DepartementRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DepartementService {
	private DepartementRepository departementRepository;

	public DepartementService(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}

	@Transactional
	public List<Departement> listDepartement() {
		return departementRepository.getListDepartement();
	}

	@Transactional
	public Departement findByDepartementId(Long id) throws DepartementNotFoundException {
		return departementRepository.findById(id);
	}

	@Transactional
	public Departement createDepartement(String name) throws ConflitDepartementException {
		try {
			departementRepository.findByName(name);
			throw new ConflitDepartementException();
		} catch (DepartementNotFoundException e) {
			return departementRepository.creaDepartement(name);
		}

	}

}
