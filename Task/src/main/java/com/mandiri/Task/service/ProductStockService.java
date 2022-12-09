package com.mandiri.Task.service;

import com.mandiri.Task.dto.ProductStockDTO;
import com.mandiri.Task.dto.ProductsDTO;
import com.mandiri.Task.dto.StoreDTO;
import com.mandiri.Task.entity.ProductStock;
import com.mandiri.Task.repository.ProductStockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductStockService {

    ProductStock createProductStock(ProductStock productStock);

    Page<ProductStock> getProductStockPage(Pageable pageable, ProductStockDTO productStockDTO);

    ProductStock updateProductStock(ProductStock productStock);

    ProductStock getProductStockById(String id);

    List<ProductStock> getAllProductStock();

    ProductStock deleteProductStockById(String id);

    ProductsDTO getAllProduct(String id);

    StoreDTO getAllStore(String id);
}
