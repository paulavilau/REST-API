package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;

@CrossOrigin("http://192.168.0.35:19000")
@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/secure/addCart")
    public Cart createCart (@RequestParam String clientId) throws Exception {
        return cartService.createNewCart(clientId);
    }

    @GetMapping("/secure/isAdded/toCart")
    public CartItem productAddedToCart(@RequestParam Integer cartId, @RequestParam Integer productId) throws Exception{
        return cartService.productAddedToCart(cartId, productId);
    }

    @PutMapping("/secure/addCartItem")
    public CartItem addItemToCart (@RequestParam Integer cartId, @RequestParam Integer productId, @RequestParam Integer quantity) throws Exception {
        return cartService.addItemToCart(cartId, productId, quantity);
    }

    @PutMapping("/secure/modifyQuantity")
    public CartItem updateCartItemQuantity(@RequestParam Integer cartItemId, @RequestParam Integer newQuantity, @RequestParam Integer isCumulated) {
        return cartService.modifyCartItemQuantity(cartItemId, newQuantity, isCumulated);
    }
}