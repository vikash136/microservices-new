package com.programmingtechie.controller;


import com.programmingtechie.dto.OrderRequest;
import com.programmingtechie.model.Order;
import com.programmingtechie.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeorder(@RequestBody OrderRequest orderRequest){
        orderServiceImpl.placeOrder(orderRequest);
        return "Order placed Successfully";
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List < Order > getAllProducts(){
        return orderServiceImpl.getAllOrderts();
    }
}
