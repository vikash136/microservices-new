package com.programmingtechie.serviceImpl;

import com.programmingtechie.dto.InventoryResponse;
import com.programmingtechie.model.Inventory;
import com.programmingtechie.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String>skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
        .stream().map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory
                                .getSkuCode())
                        .isInStock(inventory.getQuantity() > 0).build()
        ).toList();

    }

}
