package com.ishan.ecommerce.configuration;

import com.ishan.ecommerce.api.FakeStoreCategoryApi;
import com.ishan.ecommerce.api.FakeStoreProductApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public FakeStoreCategoryApi fakeStoreCategoryApi(Retrofit retrofit) {
        return retrofit.create(FakeStoreCategoryApi.class);
    }

    @Bean
    public FakeStoreProductApi fakeStoreProductApi(Retrofit retrofit) {
        return retrofit.create(FakeStoreProductApi.class);
    }

}