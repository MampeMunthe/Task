package com.mandiri.Task.controller;

import com.mandiri.Task.constant.ApiUrl;
import com.mandiri.Task.constant.ResponseMessage;
import com.mandiri.Task.dto.ProductStockDTO;
import com.mandiri.Task.dto.ProductsDTO;
import com.mandiri.Task.dto.StoreDTO;
import com.mandiri.Task.entity.ProductStock;
import com.mandiri.Task.service.serviceimpl.ProductStockServiceImpl;
import com.mandiri.Task.utils.Response;
import com.mandiri.Task.utils.exception.PageResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrl.PATH_PRODUCT_STOCK)
public class ProductStockController {

    ProductStockServiceImpl productStockService;

    @Autowired
    public ProductStockController(ProductStockServiceImpl productStockService) {
        this.productStockService = productStockService;
    }


    @GetMapping
    public List<ProductStock> getAllProductStock(){
        return productStockService.getAllProductStock();
    }

    @GetMapping("/product/{id}")
    public ProductsDTO getAllProduct(@PathVariable String id){
        return productStockService.getAllProduct(id);
    }

    @GetMapping("/store/{id}")
    public StoreDTO getAllStore(@PathVariable String id){
        return productStockService.getAllStore(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductStockById (@RequestBody String id){
        Response<ProductStock> productStockResponse = new Response<>();
        productStockResponse.setData(productStockService.getProductStockById(id));
        productStockResponse.setMessage(ResponseMessage.GET_PRODUCT_STOCK_BY_ID);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(id);
    }

    @PostMapping
    public ResponseEntity<Response<ProductStock>> saveProductStock(@RequestBody ProductStock productStock){
        Response<ProductStock> productStockResponse = new Response<>();
        productStockResponse.setData(productStockService.createProductStock(productStock));
        productStockResponse.setMessage(ResponseMessage.SAVE_PRODUCT_STOCK);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(productStockResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<ProductStock>> updateProductStock(@RequestBody ProductStock productStock){
        Response<ProductStock> productStockResponse = new Response<>();
        productStockResponse.setData(productStockService.updateProductStock(productStock));
        productStockResponse.setMessage(ResponseMessage.UPDATE_PRODUCT_STOCK);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(productStockResponse);
    }

    @GetMapping("/all")
    public PageResponseWrapper<ProductStock> getStockPerPage(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "2") Integer size,
                                                             @RequestParam(name= "sortBy",defaultValue = "productId") String sort,
                                                             @RequestParam(name="direction",defaultValue = "DESC") String direction,
                                                             @RequestParam(required = false) ProductsDTO productId,
                                                             @RequestParam(required = false) StoreDTO storeId){
        Sort sort1 = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page,size, sort1);
        ProductStockDTO productStockDTO = new ProductStockDTO();
        productStockDTO.setProductsDTO(productId);
        productStockDTO.setStoreDTO(storeId);
        Page<ProductStock> productStocks = productStockService.getProductStockPage(pageable, productStockDTO);
        return new PageResponseWrapper<>(productStocks);
    }




}
