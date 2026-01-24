package com.ishan.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<java.util.List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(adminProductService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(adminProductService.findProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        adminProductService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable Long id, @RequestBody Double price) {
        // Assuming the body is just the double value, e.g. "25.50"
        return ResponseEntity.ok(adminProductService.updateProductPriceById(id, price));
    }
}