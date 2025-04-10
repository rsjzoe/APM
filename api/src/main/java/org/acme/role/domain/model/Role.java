package org.acme.role.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    public Long id;
    public String roleName;
    public List<Permission> permissions;
}
