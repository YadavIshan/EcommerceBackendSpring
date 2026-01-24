package com.ishan.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import com.ishan.ecommerce.services.IAdminProductService;
import com.ishan.ecommerce.dto.ProductDTO;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final IAdminProductService adminProductService;

    public AdminProductController(IAdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = adminProductService.createProduct(productDTO);
        return ResponseEntity.ok(created);
    }
}