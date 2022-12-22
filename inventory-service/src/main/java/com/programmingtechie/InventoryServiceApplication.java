package com.programmingtechie;

import com.programmingtechie.model.Inventory;
import com.programmingtechie.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Autowired
    private InventoryRepository inventoryRepository;

	@Override
	public void run(String... args) throws Exception {
		Inventory inventory = new Inventory();
		inventory.setSkuCode("iphone_13");
		inventory.setQuantity(100);


		Inventory inventory1 = new Inventory();
		inventory1.setSkuCode("iphone_13_red");
		inventory1.setQuantity(0);

		inventoryRepository.save(inventory);
		inventoryRepository.save(inventory1);

	}
}
