package com.organica.payload;

public class CartHelp {
    private String UserEmail;
    private int ProductId;
    private int Quantity;

    public CartHelp(String userEmail, int productId, int quantity) {
        UserEmail = userEmail;
        ProductId = productId;
        Quantity = quantity;
    }

    public CartHelp(){}

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
