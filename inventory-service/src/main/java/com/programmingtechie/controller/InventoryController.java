package com.programmingtechie.controller;

import com.programmingtechie.dto.InventoryResponse;
import com.programmingtechie.serviceImpl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    //http://localhost:8082//api/inventory/iphone_13

    //http://localhost:8082/api/inventory?skuCode=iphone_13
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List< InventoryResponse > isInStock(@RequestParam List<String>skuCode){
        return  inventoryServiceImpl.isInStock(skuCode);
    }
}
