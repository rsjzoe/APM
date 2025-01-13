package org.acme.user.domain.input;

import org.acme.user.domain.model.Role;

// creena mitokana, satria tsy mitovy ny User sy ny User ho creena (CreateUserInput)
// ohatra: amle CreateUserInput tsy misy "id"
public class CreateUserInput {
    public String name;
    public String email;
    public Role role;
}
