package com.ishan.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishan.ecommerce.dto.ProductDTO;
import com.ishan.ecommerce.services.IProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // DUMMY Implementation for @RequestParam
    // Usage: GET /api/products?limit=10
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        List<ProductDTO> products = productService.getAllProducts(limit);
        return ResponseEntity.ok(products);
    }

    // DUMMY Implementation for @RequestBody
    // Usage: POST /api/products (with JSON body matching ProductDTO)
    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return ResponseEntity.ok(created);
    }
}
