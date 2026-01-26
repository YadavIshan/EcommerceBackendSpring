package com.ishan.ecommerce.controllers;

import com.ishan.ecommerce.dto.AllProductsOfCategoryDTO;
import com.ishan.ecommerce.dto.CategoryDTO;
import com.ishan.ecommerce.services.IAdminCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    private final IAdminCategoryService categoryService;

    public AdminCategoryController(IAdminCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<AllProductsOfCategoryDTO> getAllProductsOfCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getAllProductsOfCategory(id));
    }
}
