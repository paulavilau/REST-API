package com.example.demo.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "categorii")
@Data
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cod")
    private Integer code;
    
    @Column(name = "denumire")
    private String name;

    @Column(name = "descriere")
    private String description;

    @Column(name = "poza")
    private String image;

    // @Column(name = "id_clasa")
    // private int categoryId;

    @ManyToOne(fetch = FetchType.EAGER)   // the Category entity will only be loaded from the database when it is actually accessed in code
    @JoinColumn(name = "id_clasa")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products;
}
