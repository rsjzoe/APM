package org.acme.classe.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseOutput {
    private Long id;
    private String name;
    private String description;
    private boolean isDeleted;
}
