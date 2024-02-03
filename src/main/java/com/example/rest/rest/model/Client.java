package com.example.rest.rest.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Client {
    private Long id;
    private String name;
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Long orderId){
        orders = orders.stream().filter(o -> !o.getId().equals(orderId)).collect(Collectors.toList());
    }
}
