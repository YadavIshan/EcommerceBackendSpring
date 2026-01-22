package com.ishan.ecommerce.api;

import retrofit2.Call;
import retrofit2.http.GET;
import java.io.IOException;

public interface FakeStoreCategoryApi {

    @GET("products/categories")
    Call<java.util.List<String>> getAllFakeCategories() throws IOException;

    @GET("products/category/{categoryName}")
    Call<java.util.List<com.ishan.ecommerce.dto.FakeStoreProductDTO>> getProductsByCategory(
            @retrofit2.http.Path("categoryName") String categoryName) throws IOException;
}