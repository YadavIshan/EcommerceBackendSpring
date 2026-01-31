package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.CategoryEntity;
import com.ishan.ecommerce.entity.ProductEntity;
import com.ishan.ecommerce.exception.CategoryNotFoundException;
import com.ishan.ecommerce.mapper.CategoryMapper;
import com.ishan.ecommerce.mapper.ProductMapper;
import com.ishan.ecommerce.repository.CategoryRepository;
import com.ishan.ecommerce.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Primary
public class SelfHostedCategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    public SelfHostedCategoryService(CategoryRepository categoryRepository,
            ProductRepository productRepository,
            CategoryMapper categoryMapper,
            ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity entity = categoryMapper.toEntity(categoryDTO);
        CategoryEntity saved = categoryRepository.save(entity);
        return categoryMapper.toDTO(saved);
    }

    @Override
    public CategoryDTO getByName(String name) {
        // Assuming we might need to implement findByName in repository or just stream
        // For efficiency, repository method is better, but following pattern:
        return categoryRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(categoryMapper::toDTO)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + name));
    }

    @Override
    public AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

        List<ProductEntity> products = productRepository.findAll().stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();

        List<ProductDTO> productDTOs = products.stream()
                .map(productMapper::toDTO)
                .toList();

        return AllProductsOfCategoryDTO.builder()
                .products(productDTOs)
                .build();
    }
}
