package org.acme.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput {
    private String name;
    private String trigramme;
    private Role role;
    private String departement;

    public UserOutput(String name, String trigramme, String departement, Role role) {
        this.name = name;
        this.trigramme = trigramme;
        this.departement = departement;
        this.role = role;
    }
}
