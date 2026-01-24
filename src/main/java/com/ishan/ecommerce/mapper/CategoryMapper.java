package com.ishan.ecommerce.mapper;

import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                // ID is generated
                .build();
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
}
