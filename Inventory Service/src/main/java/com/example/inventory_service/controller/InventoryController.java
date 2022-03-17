package com.example.inventory_service.controller;

import com.example.inventory_service.Constants;
import com.example.inventory_service.entity.OrderStatus;
import com.example.inventory_service.entity.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryController {

    Product product = new Product();


    @RabbitListener(queues = Constants.QUEUE )
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        product.setProduct_id(3);
        product.setQuantity(10);
        product.setProduct_name("Apple");

        if (orderStatus.getOrder().getProduct_id()==product.getProduct_id() && product.getQuantity()>0){
            System.out.println("Order can be fulfilled");
        }
        else System.out.println("Order can't be fulfilled");

    }
}