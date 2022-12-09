package com.mandiri.Task.service.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandiri.Task.dto.ProductStockDTO;
import com.mandiri.Task.dto.ProductsDTO;
import com.mandiri.Task.dto.StoreDTO;
import com.mandiri.Task.entity.ProductStock;
import com.mandiri.Task.repository.ProductStockRepository;
import com.mandiri.Task.service.ProductStockService;
import com.mandiri.Task.spesification.ProductStockSpecification;
import com.mandiri.Task.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Predicate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStockServiceImpl implements ProductStockService {

    ProductStockRepository productStockRepository;

    RestTemplate restTemplate;

    @Autowired
    public ProductStockServiceImpl(ProductStockRepository productStockRepository, RestTemplate restTemplate) {
        this.productStockRepository = productStockRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public ProductStock createProductStock(ProductStock productStock) {
        return productStockRepository.save(productStock);

    }


    @Override
    public Page<ProductStock> getProductStockPage(Pageable pageable, ProductStockDTO productStockDTO) {
        return null;
    }

//    @Override
//    public Page<ProductStock> getProductStockPage(Pageable pageable, ProductStockDTO productStockDTO) {
//        Specification<ProductStock> productStockSpecification = ProductStockSpecification.getSpecification(productStockDTO);
//        return productStockRepository.findAll(productStockSpecification, pageable);
//    }
//    @Override
//    public ProductStock createProductStock(ProductStock productStock) {
//        ProductStock productStock1 = productStockRepository.save(productStock);
//
//        if(productStock1.getProductId() != null && productStock1.getStoreId() != null){
//            productStock1.setStock(productStock1.getStock() + productStock.getStock());
//        }else {
//            productStockRepository.save(productStock1);
//        }
//        return productStock1;
//    }

    @Override
    public ProductStock updateProductStock(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    @Override
    public ProductStock getProductStockById(String id) {
        return productStockRepository.findById(id).get();
    }

    @Override
    public List<ProductStock> getAllProductStock() {
        return productStockRepository.findAll();
    }

    @Override
    public ProductStock deleteProductStockById(String id) {
        productStockRepository.deleteById(id);
        return null;
    }

    @Override
    public ProductsDTO getAllProduct(String id) {
        //UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://10.232.100.131:8080/product/" + id);
        //ResponseEntity<ProductsDTO> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, ProductsDTO.class);
        ResponseEntity<Response> result = restTemplate.getForEntity(URI.create("http://10.232.100.131:8080/product/" + id), Response.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ProductsDTO productsDTO = objectMapper.convertValue(result.getBody().getData(), ProductsDTO.class);
        return productsDTO;
    }

    @Override
    public StoreDTO getAllStore(String id) {
        ResponseEntity<Response> result = restTemplate.getForEntity("http://10.232.100.134:8080/store/" + id, Response.class);
        ObjectMapper objectMapper = new ObjectMapper();
        StoreDTO storeDTO = objectMapper.convertValue(result.getBody().getData(), StoreDTO.class);
        return storeDTO;


    }

}
