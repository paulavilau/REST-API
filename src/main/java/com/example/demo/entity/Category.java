package com.example.demo.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "clase")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "denumire")
    private String name;

    @Column(name = "descriere")
    private String description;

    @Column(name = "poza")
    private String image;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;
}
