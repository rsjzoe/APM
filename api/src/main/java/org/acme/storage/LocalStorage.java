package org.acme.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;


public class LocalStorage implements Storage {
    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public StorageFile save(FileInput fileInput) throws IOException, FileNotFound {
        Path uploadPath = Path.of(UPLOAD_DIR);
        Path sourcePath = fileInput.getPath();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (!Files.exists(sourcePath)) {
            throw new FileNotFound("Source file does not exist: " + sourcePath);
        }

        String originalFilename = fileInput.getName();
        String timestampedFilename = System.currentTimeMillis() + "_" + originalFilename;
        Path destinationPath = uploadPath.resolve(timestampedFilename);

        Files.write(destinationPath, Files.readAllBytes(sourcePath));

        BasicFileAttributes attrs = Files.readAttributes(destinationPath,
                BasicFileAttributes.class);
        String contentType = Files.probeContentType(destinationPath);
        contentType = contentType != null ? contentType : "unknown";

        String location = timestampedFilename;
        return new StorageFile(location, originalFilename, timestampedFilename, attrs.size(), contentType,
                new HashMap<>());
    }

    @Override
    public StorageFile get(String filename) throws FileNotFound {
        Path filePath = Path.of(UPLOAD_DIR, filename);

        if (!Files.exists(filePath)) {
            throw new FileNotFound("File not found: " + filename);
        }

        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            String contentType = Files.probeContentType(filePath);

            return new StorageFile(filePath.toString(), filename, filename, attrs.size(),
                    contentType != null ? contentType : "unknown", new HashMap<>());
        } catch (IOException e) {
            throw new FileNotFound("Error reading file attributes: " + filename);
        }
    }

    @Override
    public void delete(String filename) throws FileNotFound {
        Path filePath = Path.of(UPLOAD_DIR, filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFound("File not found: " + filename);
        }
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file: " + filename, e);
        }

    }

    @Override
    public byte[] getBytes(String filename) throws FileNotFound, IOException {
        Path filePath = Path.of(UPLOAD_DIR, filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFound("File not found: " + filename);
        }
        return Files.readAllBytes(filePath);
    }

}
