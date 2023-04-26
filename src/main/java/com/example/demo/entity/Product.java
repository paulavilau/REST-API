package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "articole")
@Data
public class Product {

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

    @Column(name = "UM")
    private String unit;

    @Column(name = "masura")
    private String measure;

    @Column(name = "fr_cantitate")
    private BigDecimal frQuantity;

    @Column(name = "poza")
    private String image;

    @Column(name = "origine")
    private String origin;

    @Column(name = "sezon")
    private String season;

    @Column(name = "pret")
    private Integer price;

    // @Column(name = "id_categorie")
    // private Integer categoryId;

    @ManyToOne(fetch = FetchType.EAGER)   
    @JoinColumn(name = "id_categorie")
    private Subcategory subcategory;
    
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    // @Column(name = "id_producator")
    // private Integer supplierId;

    @ManyToOne(fetch = FetchType.EAGER)   
    @JoinColumn(name = "id_producator")
    private Supplier supplier;
}

