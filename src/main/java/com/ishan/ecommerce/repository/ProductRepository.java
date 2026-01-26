package com.ishan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ishan.ecommerce.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    java.util.List<ProductEntity> findByCategory_Id(Long categoryId);

    // Parameter is passed using param and to use that value we use colon :
    @org.springframework.data.jpa.repository.Query("SELECT p FROM products p WHERE p.category.id = :categoryId ORDER BY p.price DESC")
    java.util.List<ProductEntity> findMostExpensiveProducts(
            @org.springframework.data.repository.query.Param("categoryId") Long categoryId);

    @org.springframework.data.jpa.repository.Query("SELECT p FROM products p WHERE p.price >= :minPrice")
    java.util.List<ProductEntity> filterProductsByMinPrice(
            @org.springframework.data.repository.query.Param("minPrice") Double minPrice);
}
