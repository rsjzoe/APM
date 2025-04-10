package org.acme.role.domain.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRole {
    public String roleName;
    public List<CreatePermission> permissions;
}
