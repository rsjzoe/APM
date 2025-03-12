package org.acme.category.domain.output;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryODAParentOutput {
    private Long id;
    private String name;
    private String bgColor;
    private Boolean isDelete;
    private List<CategoryODAChildOutput> childs;
}
