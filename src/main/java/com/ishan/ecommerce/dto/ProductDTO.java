package com.ishan.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private String image;
	private String color;
	private double price;
	private String description;
	private int discount;
	private String model;
	private Long id;
	private String title;
	private Long categoryId;
	private String brand;
	private boolean popular;
	private java.time.Instant createdAt;
	private java.time.Instant updatedAt;
}