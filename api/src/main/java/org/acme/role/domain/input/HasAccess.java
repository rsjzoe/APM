package org.acme.role.domain.input;

import org.jboss.resteasy.reactive.RestQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasAccess {
    @RestQuery
    private String roleName;
    @RestQuery
    private ActionType action;
    @RestQuery
    private String serviceName;
}
