package com.ishan.ecommerce.gateway;

import java.io.IOException;
import java.util.List;

import com.ishan.ecommerce.dto.CategoryDTO;

public interface ICategoryGateway {
    List<CategoryDTO> getAllCategories() throws IOException;
}
