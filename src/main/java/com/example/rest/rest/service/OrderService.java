package com.example.rest.rest.service;

import com.example.rest.rest.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);

    Order update(Order order);
    void delete(Long id);
    void deleteByIdIn(List<Long> ids);
}
