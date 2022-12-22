package com.programmingtechie.serviceImpl;

import com.programmingtechie.model.Inventory;
import com.programmingtechie.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryServiceImpl {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
       return  inventoryRepository.findBySkuCode().isPresent();
    }
}
