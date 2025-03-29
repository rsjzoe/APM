package org.acme.user.domain.input;

import org.acme.user.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private String name;
    private String departement;
    private Role role;
}
