package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;
import com.ishan.ecommerce.entity.CategoryEntity;
import com.ishan.ecommerce.exception.ProductNotFoundException;
import com.ishan.ecommerce.exception.CategoryNotFoundException;
import com.ishan.ecommerce.mapper.ProductMapper;
import com.ishan.ecommerce.repository.ProductRepository;
import com.ishan.ecommerce.repository.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfHostedProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public SelfHostedProductService(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(productEntity);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);

        if (productDTO.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(
                            "Category not found with id: " + productDTO.getCategoryId()));
            productEntity.setCategory(category);
        }

        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(savedEntity);
    }

    @Override
    public List<ProductDTO> getAllProducts(Integer limit) {
        // Simple implementation, ignoring limit for now or just checking size
        List<ProductEntity> productEntities = productRepository.findAll();
        // If limit is provided, could stream and limit, but for now returning all
        if (limit != null && limit > 0) {
            return productEntities.stream()
                    .limit(limit)
                    .map(productMapper::toDTO)
                    .toList();
        }
        return productEntities.stream()
                .map(productMapper::toDTO)
                .toList();
    }
}
