package com.example.restaurantpol;

public class register {
    private String username;
    private static String password;
    private static String access;


    public register(String username, String password,String access) {
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public register() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
