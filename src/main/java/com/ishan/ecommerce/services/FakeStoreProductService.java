package com.ishan.ecommerce.services;

import com.ishan.ecommerce.dto.FakeStoreProductResponseDTO;
import com.ishan.ecommerce.dto.ProductDTO;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ishan.ecommerce.gateway.IProductGateway;

@Service
public class FakeStoreProductService implements IProductService {

    private final IProductGateway productGateway;

    public FakeStoreProductService(IProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public FakeStoreProductResponseDTO getProductById(Long id) throws Exception {
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts(Integer limit) {
        return this.productGateway.getAllProducts(limit);
    }
}
