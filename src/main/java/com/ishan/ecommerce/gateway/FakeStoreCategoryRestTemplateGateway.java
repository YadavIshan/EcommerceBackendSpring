package com.ishan.ecommerce.gateway;

import java.util.List;

import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.mapper.GetAllCategoriesMapper;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Component
@Qualifier("fakeStoreCategoryRestTemplateGateway")
public class FakeStoreCategoryRestTemplateGateway implements ICategoryGateway {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakeStore.category.url}")
    private String fakeStoreCategoryUrl;

    public FakeStoreCategoryRestTemplateGateway(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(fakeStoreCategoryUrl,
                String[].class);
        if (responseEntity.getBody() == null) {
            return List.of();
        }

        return GetAllCategoriesMapper.toCategoryDto(Arrays.asList(responseEntity.getBody()));

    }
}
