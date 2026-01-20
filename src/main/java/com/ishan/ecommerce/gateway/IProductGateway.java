package com.ishan.ecommerce.gateway;

import com.ishan.ecommerce.dto.ProductDTO;

import java.util.List;

public interface IProductGateway {
    ProductDTO getProductById(Long id) throws Exception;
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts(Integer limit);
}
