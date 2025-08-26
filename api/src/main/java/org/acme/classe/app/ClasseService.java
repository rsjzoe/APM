package org.acme.classe.app;

import java.util.List;

import org.acme.SocketIOServerProvider;
import org.acme.classe.domain.exception.ClasseNotFoundException;
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

    @Inject
    SocketIOServerProvider socketio;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<ClasseOutput> getListAll() {
        return classeRepository.getListAll();
    }

    @Transactional
    public ClasseOutput create(CreateClasseInput classe) {
        var created = classeRepository.create(classe);
        socketio.sendEvent("refetch_classe");
        return created;
    }

    @Transactional
    public ClasseOutput update(Long id, UpdateClasse classe) throws ClasseNotFoundException {
        var updated = classeRepository.update(id, classe);
        socketio.sendEvent("refetch_classe");
        return updated;
    }

    @Transactional
    public ClasseOutput findById(Long id) throws ClasseNotFoundException {
        return classeRepository.findById(id);
    }

    @Transactional
    public ClasseOutput deleteById(Long id) throws ClasseNotFoundException {
        var deleted = classeRepository.deleteById(id);
        socketio.sendEvent("refetch_classe");
        return deleted;
    }

}
