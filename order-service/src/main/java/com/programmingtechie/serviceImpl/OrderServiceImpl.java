package com.programmingtechie.serviceImpl;

import com.programmingtechie.dto.InventoryResponse;
import com.programmingtechie.dto.OrderLineItemsDto;
import com.programmingtechie.dto.OrderRequest;
import com.programmingtechie.model.Order;
import com.programmingtechie.model.OrderLineItems;
import com.programmingtechie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        //orderRequest.getOrderLineItemsDtoList().stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).collect(Collectors.toList());
        //after method refrence
        List < OrderLineItems > orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream().map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        List < String > skuCodes = order.getOrderLineItemsList().stream().
                map(OrderLineItems::getSkuCode)
                .toList();
        //call inventory service , and place order if product is in
        //stock
        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock , Please try again letter");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

    public List < Order > getAllOrderts() {
        return orderRepository.findAll();
    }
}
