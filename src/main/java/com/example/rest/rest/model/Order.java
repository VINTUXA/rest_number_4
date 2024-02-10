package com.example.rest.rest.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "orders") // обозначаем класс как сущность
public class Order {
    @Id // поле, которое мы используем в качестве первичного ключа
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private BigDecimal cost;
    @ManyToOne // связь будет м т о
    @JoinColumn(name = "client_id") // в таблице заказ будет столбец с внешним ключем
    @ToString.Exclude //
    private Client client;
    @CreationTimestamp // при сохранении новой сущности будет установлено время создания
    Instant createAt;
    @UpdateTimestamp // при обновлении будет обновлять запись
    private Instant updateAt;
}
