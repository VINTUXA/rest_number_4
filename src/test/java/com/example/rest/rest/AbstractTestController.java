package com.example.rest.rest;

import com.example.rest.rest.model.Client;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.web.model.ClientResponse;
import com.example.rest.rest.web.model.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

@SpringBootTest
//используется для указания что тест является интеграционным и должен загружать весь контекст приложения
@AutoConfigureMockMvc
// часть фреймворка тест для автоматической конфиграции и внедрения мок мвс и не требует ручного создания и настройки его
@ActiveProfiles("test")
public abstract class AbstractTestController {

    @Autowired
    protected MockMvc mockMvc;
    // симуляция хттп запросов и проверки ответов контроллеров

    @Autowired
    protected ObjectMapper objectMapper;
    // основной класс либы джексон для работы с джейсонами

    protected Client createClient(Long id, Order order){
        Client client = new Client(
                id,
                "Client " + id,
                new ArrayList<>()
        );
        if (order != null){
            order.setClient(client);
            client.addOrder(order);
        }

        return client;
    }

    protected Order createOrder(Long id, Long cost, Client client){
        return new Order(id, "Test product " + id, BigDecimal.valueOf(cost), client, Instant.now(), Instant.now());
    }

    protected ClientResponse createClientResponse(Long id, OrderResponse orderResponse){
        ClientResponse clientResponse = new ClientResponse(
                id,
                "Client " + id,
                new ArrayList<>()
        );
        if(orderResponse != null){
            clientResponse.getOrders().add(orderResponse);
        }
        return clientResponse;
    }

    protected OrderResponse createOrderResponse(Long id, Long cost){
        return new OrderResponse(id, "Test product " + id, BigDecimal.valueOf(cost));
    }


}
