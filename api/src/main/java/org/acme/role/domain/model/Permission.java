package org.acme.role.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    public Long id;
    public Boolean canUpdate;
    public Boolean canDelete;
    public Boolean canRead;
    public Boolean canCreate;
    public Service service;

}
