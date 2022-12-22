package com.programmingtechie.serviceImpl;

import com.programmingtechie.dto.OrderLineItemsDto;
import com.programmingtechie.dto.OrderRequest;
import com.programmingtechie.model.Order;
import com.programmingtechie.model.OrderLineItems;
import com.programmingtechie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        //orderRequest.getOrderLineItemsDtoList().stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).collect(Collectors.toList());
        //after method refrence
        List < OrderLineItems > orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setOrderLineItemsId(orderLineItemsDto.getId());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

    public List < Order > getAllOrderts() {
        return orderRepository.findAll();
    }
}
