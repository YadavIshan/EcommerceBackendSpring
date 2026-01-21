package com.ishan.ecommerce.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.gateway.ICategoryGateway;

import java.io.IOException;
import java.util.List;

@Service
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

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Not Implemented
        return null;
    }

    public CategoryDTO getByName(String name) throws Exception {
        // Not Implemented
        return null;
    }

    @Override
    public AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) {
        // Not Implemented
        return null;
    }

}