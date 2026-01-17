package com.ishan.ecommerce.mapper;

import com.ishan.ecommerce.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetAllCategoriesMapper {

	public static List<CategoryDTO> toCategoryDto(List<String> categories) {
		if (categories == null) {
			return Collections.emptyList();
		}

		List<CategoryDTO> categoryDTOS = new ArrayList<>();
		for (String category : categories) {
			CategoryDTO categoryDTO = CategoryDTO.builder()
					.name(category)
					.build();
			categoryDTOS.add(categoryDTO);
		}
		return categoryDTOS;
	}

}