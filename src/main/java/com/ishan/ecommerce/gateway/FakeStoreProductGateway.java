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
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts(Integer limit) {
        try {
            retrofit2.Response<List<ProductDTO>> response = this.fakeStoreProductApi.getAllFakeProduct(limit).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Failed to fetch products from FakeStore API. Status: " + response.code()
                        + " Error: " + response.errorBody().string());
            }

            List<ProductDTO> responseBody = response.body();

            if (responseBody == null) {
                throw new IOException("Failed to fetch products from FakeStore API");
            }

            return responseBody;
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch products", e);
        }
    }
}
