package org.acme.role.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermission {
    public Boolean canUpdate;
    public Boolean canDelete;
    public Boolean canRead;
    public Boolean canCreate;
    public Long serviceId;
}
