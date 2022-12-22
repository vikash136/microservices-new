package com.programmingtechie.repository;

import com.programmingtechie.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository< Order,Long> {
}
