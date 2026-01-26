package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.FakeStoreProductResponseDTO;
import com.ishan.ecommerce.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    FakeStoreProductResponseDTO getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts(Integer limit);
}