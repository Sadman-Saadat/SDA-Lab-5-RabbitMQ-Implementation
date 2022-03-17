package com.example.order_service.controller;

import com.example.order_service.Constants;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName ) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Order Successfully Placed to "+ restaurantName);

        rabbitTemplate.convertAndSend(Constants.EXCHANGE,Constants.ROUTING_KEY, orderStatus);
        return "success!!";
    }
}
