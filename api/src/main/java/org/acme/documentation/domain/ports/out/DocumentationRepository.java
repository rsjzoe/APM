package org.acme.documentation.domain.ports.out;

import java.util.List;

import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.input.CreateDocumentation;
import org.acme.documentation.domain.input.UpdateDocumentation;

public interface DocumentationRepository {
    List<Documentation> findDocumentationByAppId(Long id);

    Documentation createDocumentation(CreateDocumentation documentation);

    Documentation updateDocumentation(Long id, UpdateDocumentation documentation);

    Documentation deleteDocumentation(String filename);
}
