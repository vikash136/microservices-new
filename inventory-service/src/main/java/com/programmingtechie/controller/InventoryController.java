package com.programmingtechie.controller;

import com.programmingtechie.serviceImpl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return  inventoryServiceImpl.isInStock(skuCode);

    }
}
