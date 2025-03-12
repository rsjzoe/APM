package org.acme.documentation.domain.input;

import org.acme.documentation.domain.DocumentationType;
import org.acme.storage.FileInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentationFileWithoutApp {
    private DocumentationType type;
    private FileInput fileInput;


    public CreateDocumentationFileWithoutApp(FileInput fileInput, DocumentationType type) {
        this.type = type;
        this.fileInput = fileInput;
    }
}
