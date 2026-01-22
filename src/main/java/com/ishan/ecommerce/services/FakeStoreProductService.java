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
        ProductDTO product = this.productGateway.getProductById(id);
        if (product == null) {
            throw new Exception("Product not found");
        }

        // Wrap in ResponseDTO
        return FakeStoreProductResponseDTO.builder()
                .product(product)
                .message("Success")
                .status("200")
                .build();
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
