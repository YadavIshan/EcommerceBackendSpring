package com.ishan.ecommerce.api;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.dto.FakeStoreProductResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;
import java.io.IOException;

import retrofit2.http.Query;

public interface FakeStoreProductApi {
    @GET("products/{id}")
    Call<FakeStoreProductResponseDTO> getFakeProduct(@Path("id") Long id) throws IOException;

    @GET("products")
    Call<List<ProductDTO>> getAllFakeProduct(@Query("limit") Integer limit) throws IOException;
}