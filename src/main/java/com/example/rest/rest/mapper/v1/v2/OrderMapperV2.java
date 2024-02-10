package com.example.rest.rest.mapper.v1.v2;

import com.example.rest.rest.model.Order;
import com.example.rest.rest.web.model.OrderListResponse;
import com.example.rest.rest.web.model.OrderResponse;
import com.example.rest.rest.web.model.UpsetOrderRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(OrderMapperDelegate.class) // мапстракт сгенерит доп методы и обернет оригинальный маппер
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) //указывыет что интерфейс является маппером
public interface OrderMapperV2 {
    Order requestToOrder(UpsetOrderRequest request);

    @Mapping(source = "order_id", target = "id") // указание мапиинга между свойствами
    Order requestToOrder(Long orderId, UpsetOrderRequest request);

    OrderResponse orderToResponse(Order order);

    List<OrderResponse> orderListToResponseList(List<Order> orders);

    default OrderListResponse orderListToOrderListResponse(List<Order> orders){
        OrderListResponse response = new OrderListResponse();
        response.setOrders(orderListToResponseList(orders));

        return response;
    }
}
