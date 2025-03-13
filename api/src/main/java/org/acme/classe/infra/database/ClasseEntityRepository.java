package org.acme.classe.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.classe.domain.exception.ClasseNotFoundException;
import org.acme.classe.domain.input.CreateClasseInput;
import org.acme.classe.domain.input.UpdateClasse;
import org.acme.classe.domain.output.ClasseOutput;
import org.acme.classe.domain.port.out.ClasseRepository;

public class ClasseEntityRepository implements ClasseRepository {

    @Override
    public List<ClasseOutput> getListAll() {
        List<ClasseEntity> classes = ClasseEntity.list("isDeleted =? 1", false);
        return classes.stream().map(ClasseEntity::toOutput).collect(Collectors.toList());
    }

    @Override
    public ClasseOutput create(CreateClasseInput classe) {
        ClasseEntity data = new ClasseEntity(classe);
        data.persist();
        return data.toOutput();
    }

    @Override
    public ClasseOutput findById(Long id) throws ClasseNotFoundException {
        ClasseEntity entity = ClasseEntity.findById(id);
        if (entity == null)
            throw new ClasseNotFoundException();
        return entity.toOutput();
    }

    @Override
    public ClasseOutput update(Long id, UpdateClasse classe) {
        ClasseEntity entity = ClasseEntity.findById(id);
        entity.update(classe);
        entity.persist();
        return entity.toOutput();
    }

    @Override
    public ClasseOutput deleteById(Long id) {
        ClasseEntity entity = ClasseEntity.findById(id);
        entity.setDeleted(true);
        entity.persist();
        return entity.toOutput();
    }

}
