package org.acme.application.domain.query;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationQuery {
    @QueryParam("year")
    private Integer year;
    @QueryParam("search")
    private String search;
    @QueryParam("departementId")
    private Long departementId;
}
