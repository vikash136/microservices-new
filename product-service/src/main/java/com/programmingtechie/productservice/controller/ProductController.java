package com.programmingtechie.productservice.controller;

import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.model.ProductResponse;
import com.programmingtechie.productservice.serviceiml.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl  productServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productServiceImpl.createProduct(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List < ProductResponse > getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

}
