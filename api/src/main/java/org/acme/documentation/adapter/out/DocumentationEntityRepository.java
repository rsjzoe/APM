package org.acme.documentation.adapter.out;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.input.CreateDocumentation;
import org.acme.documentation.domain.input.UpdateDocumentation;
import org.acme.documentation.domain.ports.out.DocumentationRepository;

public class DocumentationEntityRepository implements DocumentationRepository {

    @Override
    public List<Documentation> findDocumentationByAppId(Long id) {
        List<DocumentationEntity> entities = DocumentationEntity.find("applicationEntity.id", id).list();
        return entities.stream().map(el -> el.toDocumentation()).collect(Collectors.toList());
    }

    @Override
    public Documentation createDocumentation(CreateDocumentation documentation) {
        DocumentationEntity created = new DocumentationEntity(documentation);
        created.persist();
        return created.toDocumentation();
    }

    @Override
    public Documentation updateDocumentation(Long id, UpdateDocumentation documentation) {
        DocumentationEntity entity = DocumentationEntity.findById(id);
        if (entity == null) {
            // FIXME: throw error
            return null;
        }

        entity.updateFrom(documentation);
        entity.persist();
        return entity.toDocumentation();

    }

    @Override
    public Documentation deleteDocumentation(String filename) {
        DocumentationEntity entity = DocumentationEntity.find("filename", filename).firstResult();
        if (entity == null) {
            // FIXME: throw error
            return null;
        }

        entity.delete();
        return entity.toDocumentation();

    }
}
