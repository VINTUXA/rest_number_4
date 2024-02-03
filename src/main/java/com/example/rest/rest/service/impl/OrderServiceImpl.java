package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.repository.OrderRepository;
import com.example.rest.rest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Order with id {0} not found", id)));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        checkForUpdate(order.getId());
        return orderRepository.update(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        orderRepository.deleteByIdIn(ids);
    }

    private void checkForUpdate(Long orderid){
        Order currentOrder = findById(orderid);
        Instant now = Instant.now();

        Duration duration = Duration.between(currentOrder.getUpdateAt(), now);

        if (duration.getSeconds() > 5){
            throw new RuntimeException("unable to update");
        }
    }
}
