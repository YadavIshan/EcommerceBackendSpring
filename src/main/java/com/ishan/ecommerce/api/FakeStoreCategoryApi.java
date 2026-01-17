package com.ishan.ecommerce.api;

import retrofit2.Call;
import retrofit2.http.GET;
import java.io.IOException;

public interface FakeStoreCategoryApi {

    @GET("products/categories")
    Call<java.util.List<String>> getAllFakeCategories() throws IOException;
}