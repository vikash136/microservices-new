package com.programmingtechie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_orderLineItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineItemsId;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;


}
