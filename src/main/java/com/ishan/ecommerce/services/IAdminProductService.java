package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;

public interface IAdminProductService {

    ProductDTO createProduct(ProductDTO productDTO);
}
