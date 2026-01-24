package com.ishan.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;
import com.ishan.ecommerce.repository.ProductRepository;
import java.util.List;

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

    @Override
    public ProductDTO updateProductPriceById(Long id, Double price) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productEntity.setPrice(price);
        ProductEntity updatedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(updatedEntity);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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
}
