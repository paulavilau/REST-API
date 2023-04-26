package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "art_cos")
@Data
public class CartItem {
    
    public CartItem() {}

    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    // @Column(name="id_cos")
    // private Integer cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cos")
    @JsonIgnore
    private Cart cart;

    // @Column(name="id_produs")
    // private Integer productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produs")
    @JsonIgnore
    private Product product;

    @Column(name="cantitate")
    private Integer quantity;

}
