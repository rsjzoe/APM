package org.acme.documentation.domain;

public class Documentation {
    private Long id;
    private String name;
    private String filename;
    private String url;
    private DocumentationType type;

    public Documentation() {
    }

    public Documentation(Long id, String name, String filename, String url, DocumentationType type) {
        this.id = id;
        this.name = name;
        this.filename = filename;
        this.url = url;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DocumentationType getType() {
        return type;
    }

    public void setType(DocumentationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Documentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
