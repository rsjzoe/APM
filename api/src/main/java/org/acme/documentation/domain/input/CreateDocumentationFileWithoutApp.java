package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;
import org.acme.storage.FileInput;

public class CreateDocumentationFileWithoutApp {
    private DocumentationType type;
    private FileInput fileInput;

    public CreateDocumentationFileWithoutApp(){}

    public CreateDocumentationFileWithoutApp(FileInput fileInput, DocumentationType type) {
        this.type = type;
        this.fileInput = fileInput;
    }

    public DocumentationType getType() {
        return type;
    }

    public void setType(DocumentationType type) {
        this.type = type;
    }

    public FileInput getFileInput() {
        return fileInput;
    }

    public void setFileInput(FileInput fileInput) {
        this.fileInput = fileInput;
    }
}
