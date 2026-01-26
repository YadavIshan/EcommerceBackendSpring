package com.ishan.ecommerce.services;

import com.ishan.ecommerce.exception.ProductNotFoundException;
import com.ishan.ecommerce.exception.CategoryNotFoundException;

import org.springframework.stereotype.Service;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;
import com.ishan.ecommerce.repository.ProductRepository;
import java.util.List;

@Service
public class AdminProductService implements IAdminProductService {
    private final ProductRepository productRepository;
    private final com.ishan.ecommerce.repository.CategoryRepository categoryRepository;
    private final com.ishan.ecommerce.mapper.ProductMapper productMapper;

    public AdminProductService(ProductRepository productRepository,
            com.ishan.ecommerce.repository.CategoryRepository categoryRepository,
            com.ishan.ecommerce.mapper.ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);

        if (productDTO.getCategoryId() != null) {
            com.ishan.ecommerce.entity.CategoryEntity category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(
                            () -> new CategoryNotFoundException(
                                    "Category not found with id: " + productDTO.getCategoryId()));
            productEntity.setCategory(category);
        }

        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(savedEntity);
    }

    @Override
    public ProductDTO updateProductPriceById(Long id, Double price) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productEntity.setPrice(price);
        ProductEntity updatedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(updatedEntity);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toDTO(productEntity);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO getMostExpensiveProductByCategory(Long categoryId) {
        List<ProductEntity> products = productRepository.findMostExpensiveProducts(categoryId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found for category id: " + categoryId);
        }
        // Since it's ordered by price DESC, the first one is the most expensive
        return productMapper.toDTO(products.get(0));
    }

    @Override
    public List<ProductDTO> findProductsByMinPrice(Double minPrice) {
        List<ProductEntity> products = productRepository.filterProductsByMinPrice(minPrice);
        return products.stream()
                .map(productMapper::toDTO)
                .toList();
    }
}
