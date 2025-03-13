package org.acme.classe.domain.port.out;

import java.util.List;

import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;

public interface ClasseRepository {
    List<ClasseOutput> getListAll();

    ClasseOutput create(CreateClasseInput classe);

    ClasseOutput update(Long id, UpdateClasse classe);

    ClasseOutput findById(Long id) throws ClasseNotFoundException;

    ClasseOutput deleteById(Long id);
}
