package org.acme.role.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Long id;
    private Boolean canUpdate;
    private Boolean canDelete;
    private Boolean canRead;
    private Boolean canCreate;
    private Service service;

}
