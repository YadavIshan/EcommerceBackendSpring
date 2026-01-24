package com.ishan.ecommerce.mapper;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return ProductEntity.builder()
                .title(productDTO.getTitle())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .category(productDTO.getCategoryId() != null ? String.valueOf(productDTO.getCategoryId()) : null)
                .image(productDTO.getImage())
                .build();
    }

    public ProductDTO toDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return ProductDTO.builder()
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                // Assuming category is stored as ID string for now
                .categoryId(productEntity.getCategory() != null ? tryParseLong(productEntity.getCategory()) : null)
                .image(productEntity.getImage())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }

    private Long tryParseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
