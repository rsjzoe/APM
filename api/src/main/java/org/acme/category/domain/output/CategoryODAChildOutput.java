package org.acme.category.domain.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryODAChildOutput {
    private Long id;
    private String name;
    private Boolean isDelete;
    private Long parentId;
    private String parentName;
}
