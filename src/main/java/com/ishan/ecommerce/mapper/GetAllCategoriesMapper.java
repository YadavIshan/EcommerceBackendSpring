package com.ishan.ecommerce.mapper;

import com.ishan.ecommerce.dto.CategoryDTO;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GetAllCategoriesMapper {

	public static List<CategoryDTO> toCategoryDto(List<String> categories) {
		if (categories == null) {
			return Collections.emptyList();
		}

		AtomicLong id = new AtomicLong(0L);
		List<CategoryDTO> categoryDTOS = categories.stream()
				.map(category -> CategoryDTO.builder().id(id.getAndIncrement()).name(category).build())
				.toList();
		return categoryDTOS;
	}

}