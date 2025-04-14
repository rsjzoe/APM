package org.acme.auth.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    private String name;
    private String trigramme;
    private String departement;
    private String role;
}
