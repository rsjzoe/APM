package org.acme.role.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermission {
    private Boolean canUpdate;
    private Boolean canDelete;
    private Boolean canRead;
    private Boolean canCreate;
    private Long serviceId;

    public void correctPermission() {
        if (canUpdate) {
            canRead = true;
        }
        if (canDelete) {
            canRead = true;
        }
        if (canCreate) {
            canRead = true;
        }
    }
}
