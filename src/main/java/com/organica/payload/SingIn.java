package com.organica.payload;


public class SingIn {

    private String Email;
    private String Password;
    private String jwt;

    public SingIn(String email, String password, String jwt) {
        Email = email;
        Password = password;
        this.jwt = jwt;
    }

    public SingIn(){}

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
