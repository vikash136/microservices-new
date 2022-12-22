package com.programmingtechie.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List <OrderLineItems> orderLineItemsList;


}
