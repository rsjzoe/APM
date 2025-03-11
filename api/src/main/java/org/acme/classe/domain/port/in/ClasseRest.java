package org.acme.classe.domain.port.in;

import java.util.List;

import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;

public interface ClasseRest {
    List<ClasseOutput> getListAll();

    ClasseOutput create(CreateClasseInput classe);

    ClasseOutput update(Long id, UpdateClasse classe);

    ClasseOutput findById(Long id);

    ClasseOutput deleteById(Long id);
}
