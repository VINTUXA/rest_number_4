package com.example.rest.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name")
    private String name;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)// маппед указывает на обратное отображение к отношение многие к одномк
    @ToString.Exclude // каскад указывает какие оперции по сохра обновлению и тд должны быть применены к связанной сущности
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        if (order == null) orders = new ArrayList<>();
        orders.add(order);
    }

    public void removeOrder(Long orderId){
        orders = orders.stream().filter(o -> !o.getId().equals(orderId)).collect(Collectors.toList());
    }

//    public List<g>

    public List<Order> getOrders() {
        if (orders == null) orders = new ArrayList<>();
        return orders;
    }
}
