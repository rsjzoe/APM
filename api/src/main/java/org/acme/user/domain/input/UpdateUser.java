package org.acme.user.domain.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private String name;
    private String departement;
    private String role;
}
