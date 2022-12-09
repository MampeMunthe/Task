package com.mandiri.Task.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class ProductsDTO {
    private String id;
    private String productName;
    private Integer productPrice;


}
