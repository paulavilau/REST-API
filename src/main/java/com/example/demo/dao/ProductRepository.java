package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
