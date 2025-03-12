package org.acme.storage;

import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInput {
    private Path path;
    private String name;
}
