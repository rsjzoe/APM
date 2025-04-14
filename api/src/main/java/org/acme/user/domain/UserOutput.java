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
    private String role;
    private String departement;
}
