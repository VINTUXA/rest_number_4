package com.example.rest.rest.web.model.v1;

import com.example.rest.rest.mapper.v1.OrderMapper;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.service.OrderService;
import com.example.rest.rest.web.model.OrderListResponse;
import com.example.rest.rest.web.model.OrderResponse;
import com.example.rest.rest.web.model.UpsetOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll(){
        return ResponseEntity.ok(orderMapper.orderListToOrderListResponse(orderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                orderMapper.orderToResponse(orderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody UpsetOrderRequest upsetOrderRequest){
        Order newOrder = orderService.save(orderMapper.requestToOrder(upsetOrderRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToResponse(newOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id,
                                                @RequestBody UpsetOrderRequest request){
        Order updatedOrder = orderService.update(orderMapper.requestToOrder(id, request));
        return ResponseEntity.ok(orderMapper.orderToResponse(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
