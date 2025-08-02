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
    @QueryParam("page")
    private Integer page = 1;
    @QueryParam("size")
    private Integer size = 25;
    
    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
}
