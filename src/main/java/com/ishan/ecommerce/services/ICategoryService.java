package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;


import java.io.IOException;
import java.util.List;


public interface ICategoryService {
    List<CategoryDTO> getAllCategories() throws IOException;
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getByName(String name) throws Exception;
    AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) throws Exception;
}
