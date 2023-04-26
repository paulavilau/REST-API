package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }
    
    public Cart createNewCart(String clientId) {
        Cart validatedCart = cartRepository.findByClientId(clientId);

        if(validatedCart != null) {
            // throw new Exception("The client already has an active cart.");
            return validatedCart;
        }

        Cart cart = new Cart( 
            clientId,
            LocalDate.now().toString()
        );

        cartRepository.save(cart);

        return cart;
    }

    public Cart getCartById(Integer cartId) throws Exception{
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new Exception("Cart with id "+cartId+" not found");
        }
    
    }

    public CartItem addItemToCart(Integer cartId, Integer productId, Integer quantity) throws Exception{

        Cart cart = getCartById(cartId);

        Optional<Product> product = productRepository.findById(productId);

        if (!product.isPresent()){
            throw new Exception("Product doesn't exist");
        }

        Optional<CartItem> cartItem = cart.getCartItems().stream()
            .filter(ci -> ci.getProduct().getId().equals(productId))
            .findFirst(); 

       if (cartItem.isPresent()) {
            // Update quantity if the item already exists in cart
            CartItem existingCartItem = cartItem.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity()+quantity);
            System.out.println(existingCartItem.getId());
            return existingCartItem;
       } else {
            // Create new cart item if item isn't already added to cart
            Product productToAdd = product.get();
            CartItem newCartItem = new CartItem(productToAdd, quantity);
            cart.addCartItem(newCartItem);
            cartRepository.save(cart);

            System.out.println(newCartItem.getId());

            return newCartItem;
       }    
    }

    public CartItem productAddedToCart(Integer cartId, Integer productId) throws Exception{
        CartItem validatedCartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if (validatedCartItem != null) {
            return validatedCartItem;
        } else {
            throw new Exception("Product isn't added to cart");
        }
    }

    public CartItem modifyCartItemQuantity(Integer cartItemId, Integer newQuantity, Integer isCumulated){

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if(isCumulated>0){
            cartItem.get().setQuantity(cartItem.get().getQuantity()+newQuantity);
        } else {
            cartItem.get().setQuantity(newQuantity);
        }

        cartItemRepository.save(cartItem.get());

        return cartItem.get();
    }
}
    

// Fara relatii

// public CartItem addItemToCart(Integer cartId, Integer productId, Integer quantity) throws Exception{  

//     Optional<Product> product = productRepository.findById(productId);

//     Optional<CartItem> validatedCartItem = cartItemRepository.findById(cartItemId);

//     if(!product.isPresent() || validatedCartItem != null) {
//         throw new Exception("Product doesn't exist or it's already been added to cart");
//         //return validatedCartItem;
//     }

//     CartItem cartItem = new CartItem(
//         cartId, 
//         productId, 
//         quantity
//     );

//     cartItemRepository.save(cartItem);

//     // return product.get();
//     return cartItem;
// }
