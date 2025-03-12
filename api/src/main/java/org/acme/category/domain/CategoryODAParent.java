package org.acme.category.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryODAParent {
    private Long id;
    private String name;
    private String bgColor;
    private Boolean isDelete;
    private List<CategoryODAChild> childs;
}