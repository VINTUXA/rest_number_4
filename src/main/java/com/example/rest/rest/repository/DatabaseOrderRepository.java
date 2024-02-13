package com.example.rest.rest.repository;

import com.example.rest.rest.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatabaseOrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByProduct(String product, Pageable pageable);

//    @Query("SELECT o FROM com.example.rest.rest.model.Order o where o.product = :productName")
//    List<Order> getByProduct(String productName);

//    @Query(value = "SELECT * FROM products o where o.product = :productName", nativeQuery = true)
//    List<Order> getByProduct(String productName);


}
