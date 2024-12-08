package com.organica.entities;


import jakarta.persistence.*;

@Entity
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CartDetailsId;

    @ManyToOne
    private Product products;
    private int Quantity;
    private int Amount;

    @ManyToOne
    private Cart cart;

    public CartDetails(int cartDetailsId, Product products, int quantity, int amount, Cart cart) {
        CartDetailsId = cartDetailsId;
        this.products = products;
        Quantity = quantity;
        Amount = amount;
        this.cart = cart;
    }
    public CartDetails(){}

    public int getCartDetailsId() {
        return CartDetailsId;
    }

    public void setCartDetailsId(int cartDetailsId) {
        CartDetailsId = cartDetailsId;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
