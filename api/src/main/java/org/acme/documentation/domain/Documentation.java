package org.acme.documentation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documentation {
    private Long id;
    private String name;
    private String filename;
    private String url;
    private DocumentationType type;

}
