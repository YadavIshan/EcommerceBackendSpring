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
                // Category must be set by the service using categoryId
                .category(null)
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
                .categoryId(productEntity.getCategory() != null ? productEntity.getCategory().getId() : null)
                .image(productEntity.getImage())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }

}
