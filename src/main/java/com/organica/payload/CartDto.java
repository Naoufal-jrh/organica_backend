package com.organica.payload;


import java.util.List;

public class CartDto {
    private int Id;

    private UserDto user;

    private float TotalAmount;

    private List<CartDetailDto> cartDetails;

    public CartDto(int id, UserDto user, float totalAmount, List<CartDetailDto> cartDetails) {
        Id = id;
        this.user = user;
        TotalAmount = totalAmount;
        this.cartDetails = cartDetails;
    }

    public CartDto(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public float getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        TotalAmount = totalAmount;
    }

    public List<CartDetailDto> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetailDto> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
