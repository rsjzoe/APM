package org.acme.role.domain.model.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRole {
    private String roleName;
    private List<CreatePermission> permissions;

    public void correctRole() {
        for (var permission : permissions) {
            permission.correctPermission();
        }
    }
}
