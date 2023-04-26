package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;
import javax.persistence.*;


@Entity
@Table(name = "cosuri")
@Data
public class Cart {
    
    public Cart() {}

    public Cart(String clientId, String creationDt) {
        this.clientId = clientId;
        this.creationDt = creationDt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="idClient")
    private String clientId;

    @Column(name="dtCreare")
    private String creationDt;

    @Column(name="valoare")
    private BigDecimal value;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }
    
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setCart(null);
 }

}
