package com.example.demo.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "producatori")
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nume")
    private String name;
    
    @Column(name = "judet")
    private String county;

    @Column(name = "localitate")
    private String locality;

    @Column(name = "adresa")
    private String adress;

    @Column(name = "telefon")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "pers_contact")
    private String contactPerson;

    @Column(name = "poza")
    private String image;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
