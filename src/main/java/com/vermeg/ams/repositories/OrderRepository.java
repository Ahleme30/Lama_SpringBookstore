package com.vermeg.ams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.ams.entities.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
