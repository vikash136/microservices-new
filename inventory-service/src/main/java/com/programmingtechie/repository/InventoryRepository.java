package com.programmingtechie.repository;

import com.programmingtechie.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository< Inventory,Long > {
    @Query("select i from Inventory i where i.skuCode = ?1")
    Optional<Inventory> findBySkuCode();
}
