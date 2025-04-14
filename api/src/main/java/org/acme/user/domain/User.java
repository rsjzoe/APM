package org.acme.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String trigramme;
    private String department;
    private String role;
    private String password;

    public UserOutput toOutput() {
        return new UserOutput(name, trigramme, department, role);
    }
}
