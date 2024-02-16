package com.example.rest.rest.service.impl;

import com.example.rest.rest.aop.Loggable;
import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.repository.DatabaseClientRepository;
import com.example.rest.rest.repository.DatabaseOrderRepository;
import com.example.rest.rest.service.ClientService;
import com.example.rest.rest.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Primary
public class DatabaseClientService implements ClientService {
    private final DatabaseClientRepository clientRepository;
    private final DatabaseOrderRepository orderRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Client with id {} not found!", id
        )));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client existedClient = findById(client.getId());

        BeanUtils.copyNonNullProperties(client, existedClient);
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Loggable
    public Client saveWithOrders(Client client, List<Order> orders) {
        Client savedClient = clientRepository.save(client);

        for (Order order: orders){
            order.setClient(savedClient);
            var savedOrder = orderRepository.save(order);
            savedClient.addOrder(savedOrder);
        }
        return savedClient;
    }
}
