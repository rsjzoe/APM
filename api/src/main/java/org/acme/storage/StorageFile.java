package org.acme.storage;

import java.util.Map;

public class StorageFile {
    private String url;
    private String name;
    private String filename;
    private long size;
    private String contentType;
    private final Map<String, String> metadata;

    public StorageFile(String url, String name, String filename, long size, String contentType,
            Map<String, String> metadata) {
        this.url = url;
        this.filename = filename;
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.metadata = metadata;
    }

    public StorageFile(String url, String name, String filename, long size, String contentType) {
        this(url, name, filename, size, contentType, Map.of());
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public String getContentType() {
        return contentType;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "StorageFile{" +
                "url='" + url + '\'' +
                ", filename='" + filename + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
