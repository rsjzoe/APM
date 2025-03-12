package org.acme.classe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    private String id;
    private String name;
    private String description;
    private boolean isDeleted;
}
