package com.ishan.ecommerce.api;

import com.ishan.ecommerce.dto.FakeStoreProductResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;

public interface FakeStoreProductApi {
    @GET("products/{id}")
    Call<FakeStoreProductResponseDTO> getFakeProduct(@Path("id") Long id) throws IOException;
}