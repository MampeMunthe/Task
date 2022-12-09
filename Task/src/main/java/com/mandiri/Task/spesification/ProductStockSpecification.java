package com.mandiri.Task.spesification;

import com.mandiri.Task.dto.ProductStockDTO;
import com.mandiri.Task.entity.ProductStock;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductStockSpecification {
    public static Specification<ProductStock> getSpecification (ProductStockDTO productStockDTO){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (productStockDTO.getProductsDTO().getId() != null) {
                Predicate productIdPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("productId")),
                        "%" + productStockDTO.getProductsDTO().getId().toLowerCase() + "%");
                predicates.add(productIdPredicate);

            }

            if (productStockDTO.getStoreDTO().getId() != null) {
                Predicate storeIdPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("storeId")),
                        "%" + productStockDTO.getStoreDTO().getId().toLowerCase() + "%");
                predicates.add(storeIdPredicate);
            }
            Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);

            return criteriaBuilder.and(arrayPredicate);

        };
    }
}
