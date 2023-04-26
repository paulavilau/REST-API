package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart ,Integer>{

    Cart findByClientId(@RequestParam("clientId") String clientId);
    
}
