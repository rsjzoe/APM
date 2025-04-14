package org.acme.documentation.domain.ports.in;

import java.util.List;

import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.DocumentationType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.Response;

public interface DocumentationRest {
    List<Documentation> findDocumentationByAppId(Long id);

    Documentation createDocumentation(FileUpload file, Long applicationId, DocumentationType type, String authHeader);

    Response getDocumentation(String filename);

    Documentation deleteDocumentation(String filename, String authHeader);
}
