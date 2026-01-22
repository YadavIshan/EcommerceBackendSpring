package com.ishan.ecommerce.gateway;

import com.ishan.ecommerce.dto.CategoryDTO;

import com.ishan.ecommerce.api.FakeStoreCategoryApi;
import com.ishan.ecommerce.mapper.GetAllCategoriesMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("fakeStoreRestTemplateGateway")
public class FakeStoreCategoryGateway implements ICategoryGateway {

    private final FakeStoreCategoryApi fakeStoreCategoryApi;

    public FakeStoreCategoryGateway(FakeStoreCategoryApi fakeStoreCategoryApi) {
        this.fakeStoreCategoryApi = fakeStoreCategoryApi;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        // 1. Make the HTTP request to the FakeStore API to fetch all categories
        retrofit2.Response<List<String>> response = this.fakeStoreCategoryApi.getAllFakeCategories()
                .execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed to fetch categories from FakeStore API. Status: " + response.code()
                    + " Error: " + response.errorBody().string());
        }

        List<String> responseBody = response.body();

        // 2. Check if the response is null and throw an IOException if it is
        if (responseBody == null) {
            throw new IOException("Failed to fetch categories from FakeStore API");
        }

        // 3. Map the response to a list of CategoryDTO objects
        return GetAllCategoriesMapper.toCategoryDto(responseBody);
    }

    @Override
    public List<com.ishan.ecommerce.dto.FakeStoreProductDTO> getProductsByCategory(String categoryName) {
        try {
            retrofit2.Response<java.util.List<com.ishan.ecommerce.dto.FakeStoreProductDTO>> response = this.fakeStoreCategoryApi
                    .getProductsByCategory(categoryName).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Failed to fetch products for category. Status: " + response.code());
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch products", e);
        }
    }
}