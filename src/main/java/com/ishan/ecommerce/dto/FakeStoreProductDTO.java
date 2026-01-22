package com.ishan.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating {
        private double rate;
        private int count;
    }
}
