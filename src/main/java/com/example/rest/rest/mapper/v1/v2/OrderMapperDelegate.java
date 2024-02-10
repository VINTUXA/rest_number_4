package com.example.rest.rest.mapper.v1.v2;

import com.example.rest.rest.model.Order;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.web.model.UpsetOrderRequest;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OrderMapperDelegate implements OrderMapperV2{

    @Autowired
    private ClientService databaseClientService;

    @Override
    public Order requestToOrder(UpsetOrderRequest request){
        Order order = new Order();
        order.setCost(request.getCost());
        order.setProduct(request.getProduct());
        order.setClient(databaseClientService.findById(request.getClientId()));

        return order;
    }

    @Override
    public Order requestToOrder(Long orderId, UpsetOrderRequest request){
        Order order = new Order();
        order.setId(orderId);

        return order;
    }

}
