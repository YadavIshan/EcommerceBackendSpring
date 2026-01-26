package com.ishan.ecommerce.services;

import java.util.List;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.entity.ProductEntity;

public interface IAdminProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProductPriceById(Long id, Double price);

    ProductDTO findProductById(Long id);

    void deleteProductById(Long id);

    List<ProductDTO> findAllProducts();

    ProductDTO getMostExpensiveProductByCategory(Long categoryId);

    List<ProductDTO> findProductsByMinPrice(Double minPrice);
}
