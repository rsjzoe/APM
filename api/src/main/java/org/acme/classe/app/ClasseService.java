package org.acme.classe.app;

import java.util.List;

import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.domain.port.out.ClasseRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClasseService {
    @Inject
    ClasseRepository classeRepository;

    public List<ClasseOutput> getListAll() {
        return classeRepository.getListAll();
    }

    @Transactional
    public ClasseOutput create(CreateClasseInput classe) {
        return classeRepository.create(classe);
    }

    @Transactional
    public ClasseOutput update(Long id, UpdateClasse classe) {
        return classeRepository.update(id, classe);
    }

    @Transactional
    public ClasseOutput findById(Long id) {
        return classeRepository.findById(id);
    }

    @Transactional
    public ClasseOutput deleteById(Long id) {
        return classeRepository.deleteById(id);
    }

}
