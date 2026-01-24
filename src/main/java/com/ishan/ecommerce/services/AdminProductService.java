package com.ishan.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;
import com.ishan.ecommerce.repository.ProductRepository;

@Service
public class AdminProductService implements IAdminProductService {
    private final ProductRepository productRepository;
    private final com.ishan.ecommerce.mapper.ProductMapper productMapper;

    public AdminProductService(ProductRepository productRepository,
            com.ishan.ecommerce.mapper.ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(savedEntity);
    }

}
