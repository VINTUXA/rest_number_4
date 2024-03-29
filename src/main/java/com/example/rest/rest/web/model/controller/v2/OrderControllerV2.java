package com.example.rest.rest.web.model.controller.v2;

import com.example.rest.rest.mapper.v1.OrderMapper;
import com.example.rest.rest.mapper.v1.v2.OrderMapperV2;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.service.OrderService;
import com.example.rest.rest.web.model.OrderFilter;
import com.example.rest.rest.web.model.OrderListResponse;
import com.example.rest.rest.web.model.OrderResponse;
import com.example.rest.rest.web.model.UpsetOrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/order")
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderMapperV2 orderMapper;
    private final OrderService databaseOrderService;

    @GetMapping("/filter")
    public ResponseEntity<OrderListResponse> filterBy(@Valid OrderFilter filter){
//        OrderFilter filter = new OrderFilter();
//        filter.setProductName(productName);
        return ResponseEntity.ok(
                orderMapper.orderListToOrderListResponse(databaseOrderService.filterBy(filter))
        );
    }

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll(){
        return ResponseEntity.ok(orderMapper.orderListToOrderListResponse(databaseOrderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                orderMapper.orderToResponse(databaseOrderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody UpsetOrderRequest upsetOrderRequest){ // можно валид добавить
        Order newOrder = databaseOrderService.save(orderMapper.requestToOrder(upsetOrderRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToResponse(newOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id,
                                                @RequestBody UpsetOrderRequest request){
        Order updatedOrder = databaseOrderService.update(orderMapper.requestToOrder(id, request));
        return ResponseEntity.ok(orderMapper.orderToResponse(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        databaseOrderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

