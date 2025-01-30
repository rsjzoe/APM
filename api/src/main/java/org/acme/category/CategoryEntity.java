package org.acme.category;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CategoryEntity extends PanacheEntity {
    public String name;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String name) {
        this.name = name;
    }

    public Category toCategory() {
        return new Category(id, name);
    }

}
