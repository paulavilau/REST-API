package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
}

