package com.ishan.ecommerce.gateway;

import org.springframework.stereotype.Component;

import com.ishan.ecommerce.api.FakeStoreProductApi;
import com.ishan.ecommerce.dto.ProductDTO;

import java.io.IOException;
import java.util.List;

@Component("fakeStoreProductGateway")
public class FakeStoreProductGateway implements IProductGateway {

    private final FakeStoreProductApi fakeStoreProductApi;

    public FakeStoreProductGateway(FakeStoreProductApi fakeStoreProductApi) {
        this.fakeStoreProductApi = fakeStoreProductApi;
    }

    @Override
    public ProductDTO getProductById(Long id) throws Exception {
        try {
            retrofit2.Response<com.ishan.ecommerce.dto.FakeStoreProductDTO> response = this.fakeStoreProductApi
                    .getFakeProduct(id).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Failed to fetch product from FakeStore API. Status: " + response.code()
                        + " Error: " + response.errorBody().string());
            }

            com.ishan.ecommerce.dto.FakeStoreProductDTO fakeProduct = response.body();

            if (fakeProduct == null) {
                throw new IOException("Failed to fetch product from FakeStore API");
            }

            return mapToProductDTO(fakeProduct);

        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch product", e);
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            // Map Input ProductDTO -> FakeStoreProductDTO
            // Note: FakeStore API expects 'category' as String, but we might not have it or
            // it might be null.
            // Using logic to provide a default if missing, or use what is there.
            // Since ProductDTO has categoryId (Long), we can't easily map it to String
            // category without lookup.
            // We will send "electronics" as placeholder or empty string to ensure API
            // accepts it.

            com.ishan.ecommerce.dto.FakeStoreProductDTO fakeInput = com.ishan.ecommerce.dto.FakeStoreProductDTO
                    .builder()
                    .title(productDTO.getTitle())
                    .price(productDTO.getPrice())
                    .description(productDTO.getDescription())
                    .image(productDTO.getImage())
                    .category("electronics") // Defaulting for now as we don't have name lookup here
                    .build();

            retrofit2.Response<com.ishan.ecommerce.dto.FakeStoreProductDTO> response = this.fakeStoreProductApi
                    .createProduct(fakeInput).execute();

            if (!response.isSuccessful()) {
                try {
                    throw new IOException("Failed to create product. Status: " + response.code()
                            + " Error: " + response.errorBody().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return mapToProductDTO(response.body());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create product", e);
        }
    }

    @Override
    public List<ProductDTO> getAllProducts(Integer limit) {
        try {
            retrofit2.Response<List<com.ishan.ecommerce.dto.FakeStoreProductDTO>> response = this.fakeStoreProductApi
                    .getAllFakeProduct(limit).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Failed to fetch products from FakeStore API. Status: " + response.code()
                        + " Error: " + response.errorBody().string());
            }

            List<com.ishan.ecommerce.dto.FakeStoreProductDTO> responseBody = response.body();

            if (responseBody == null) {
                throw new IOException("Failed to fetch products from FakeStore API");
            }

            return responseBody.stream().map(this::mapToProductDTO).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch products", e);
        }
    }

    private ProductDTO mapToProductDTO(com.ishan.ecommerce.dto.FakeStoreProductDTO fakeStoreProductDTO) {
        if (fakeStoreProductDTO == null)
            return null;
        return ProductDTO.builder()
                .id(fakeStoreProductDTO.getId())
                .title(fakeStoreProductDTO.getTitle())
                .price(fakeStoreProductDTO.getPrice())
                .description(fakeStoreProductDTO.getDescription())
                .image(fakeStoreProductDTO.getImage())
                // .categoryId() // Cannot map from String category to Long categoryId without
                // extra logic
                .build();
    }
}
