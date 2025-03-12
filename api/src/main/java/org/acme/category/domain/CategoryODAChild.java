package org.acme.category.domain;

import org.acme.category.domain.output.CategoryODAChildOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryODAChild {
    private Long id;
    private String name;
    private Boolean isDelete;
    private CategoryODAParent parent;

    public CategoryODAChildOutput toOdaChildOutput() {
        return new CategoryODAChildOutput(id, name, isDelete, parent == null ? null : parent.getId(),
                parent == null ? null : parent.getName());
    }

}