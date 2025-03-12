package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentation {
    private String name;
    private String filename;
    private String url;
    private DocumentationType type;
    private Long applicationId;
}
