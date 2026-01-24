package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;

import java.util.List;

public interface IAdminCategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO getByName(String name) throws Exception;

    AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) throws Exception;
}
