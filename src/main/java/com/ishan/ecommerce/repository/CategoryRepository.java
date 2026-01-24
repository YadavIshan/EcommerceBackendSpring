package com.ishan.ecommerce.repository;

import com.ishan.ecommerce.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    java.util.Optional<CategoryEntity> findByName(String name);
}
