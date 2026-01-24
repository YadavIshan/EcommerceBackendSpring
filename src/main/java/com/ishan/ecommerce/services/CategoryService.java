package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.CategoryEntity;
import com.ishan.ecommerce.entity.ProductEntity;
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
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper,
            ProductRepository productRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public CategoryDTO getByName(String name) throws Exception {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(() -> new Exception("Category with name " + name + " not found"));
        return categoryMapper.toDTO(categoryEntity);
    }

    @Override
    public AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) throws Exception {
        if (!categoryRepository.existsById(categoryId)) {
            throw new Exception("Category with id " + categoryId + " not found");
        }
        List<ProductEntity> products = productRepository.findByCategory_Id(categoryId);
        List<ProductDTO> productDTOs = products.stream()
                .map(productMapper::toDTO)
                .toList();

        return AllProductsOfCategoryDTO.builder()
                .products(productDTOs)
                .build();
    }
}
