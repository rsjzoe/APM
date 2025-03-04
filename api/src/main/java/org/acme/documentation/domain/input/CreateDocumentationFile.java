package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;
import org.acme.storage.FileInput;

public class CreateDocumentationFile {
    private DocumentationType type;
    private Long applicationId;
    private FileInput fileInput;

    public CreateDocumentationFile(FileInput fileInput, DocumentationType type, Long applicationId) {
        this.type = type;
        this.applicationId = applicationId;
        this.fileInput = fileInput;
    }

    public DocumentationType getType() {
        return type;
    }

    public void setType(DocumentationType type) {
        this.type = type;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public FileInput getFileInput() {
        return fileInput;
    }

    public void setFileInput(FileInput fileInput) {
        this.fileInput = fileInput;
    }
}
