package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.exception.ProductNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ishan.ecommerce.gateway.IProductGateway;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    private final IProductGateway productGateway;

    public FakeStoreProductService(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductDTO product;
        try {
            product = this.productGateway.getProductById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch product with id: " + id, e);
        }

        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        return product;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return this.productGateway.createProduct(productDTO);
    }

    @Override
    public List<ProductDTO> getAllProducts(Integer limit) {
        return this.productGateway.getAllProducts(limit);
    }
}
