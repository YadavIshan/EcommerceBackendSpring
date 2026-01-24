package com.ishan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "products") // Use table annotation when you just want to change the table name and entity
// annotation when you want to change the class name and jpql usage
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductEntity extends BaseEntity {
    private String title;
    private Double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryEntity category;

    private String image;
}
