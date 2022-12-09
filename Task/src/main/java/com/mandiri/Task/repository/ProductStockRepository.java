package com.mandiri.Task.repository;

import com.mandiri.Task.entity.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository <ProductStock, String> {
    Page<ProductStock> findAll(Specification<ProductStock> productStockSpecification, Pageable pageable);
}
