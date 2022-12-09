package com.mandiri.Task.utils;

import lombok.Data;

@Data
public class Response <T>{
    private String message;
    private T data;


}
