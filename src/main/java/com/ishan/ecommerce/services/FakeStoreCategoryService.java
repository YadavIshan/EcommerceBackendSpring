package com.ishan.ecommerce.services;

import com.ishan.ecommerce.exception.CategoryNotFoundException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.gateway.ICategoryGateway;

import java.io.IOException;
import java.util.List;

@Service("fakeStoreCategoryService")
public class FakeStoreCategoryService implements ICategoryService {

        private final ICategoryGateway categoryGateway;

        public FakeStoreCategoryService(
                        @Qualifier("fakeStoreCategoryRestTemplateGateway") ICategoryGateway categoryGateway) {
                this.categoryGateway = categoryGateway;
        }

        @Override
        public List<CategoryDTO> getAllCategories() throws IOException {
                return this.categoryGateway.getAllCategories();
        }

        @Override
        public CategoryDTO createCategory(CategoryDTO categoryDTO) {
                // Implementation for creating category (mock)
                return CategoryDTO.builder()
                                .id(999L)
                                .name(categoryDTO.getName())
                                .build();
        }

        @Override
        public CategoryDTO getByName(String name) {
                try {
                        return getAllCategories().stream()
                                        .filter(cat -> cat.getName().equalsIgnoreCase(name))
                                        .findFirst()
                                        .orElseThrow(() -> new CategoryNotFoundException(
                                                        "Category not found: " + name));
                } catch (IOException e) {
                        throw new RuntimeException("Failed to fetch categories", e);
                }
        }

        @Override
        public AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) {
                // 1. Find category name by ID
                CategoryDTO category;
                try {
                        category = getAllCategories().stream()
                                        .filter(cat -> cat.getId().equals(categoryId))
                                        .findFirst()
                                        .orElseThrow(() -> new CategoryNotFoundException(
                                                        "Category ID not found: " + categoryId));
                } catch (IOException e) {
                        throw new RuntimeException("Failed to fetch categories", e);
                }

                // 2. Fetch products form Gateway
                List<com.ishan.ecommerce.dto.FakeStoreProductDTO> fakeProducts = categoryGateway
                                .getProductsByCategory(category.getName());

                // 3. Map to ProductDTO
                List<com.ishan.ecommerce.dto.ProductDTO> products = fakeProducts.stream()
                                .map(fp -> com.ishan.ecommerce.dto.ProductDTO.builder()
                                                .id(fp.getId())
                                                .title(fp.getTitle())
                                                .price(fp.getPrice())
                                                .description(fp.getDescription())
                                                .image(fp.getImage())
                                                .categoryId(categoryId)
                                                .build())
                                .toList();

                return AllProductsOfCategoryDTO.builder()
                                .products(products)
                                .build();
        }
}