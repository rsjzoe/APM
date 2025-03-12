package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;
import org.acme.storage.FileInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentationFile {
    private DocumentationType type;
    private Long applicationId;
    private FileInput fileInput;

    public CreateDocumentationFile(FileInput fileInput, DocumentationType type, Long applicationId) {
        this.type = type;
        this.applicationId = applicationId;
        this.fileInput = fileInput;
    }
}
