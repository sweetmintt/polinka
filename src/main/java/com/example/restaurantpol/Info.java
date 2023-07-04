package com.example.restaurantpol;
public class Info {
    private String ingredient_name;

    private int quantity_on_hand;

    private String dishes_name;

    private int quantity_required;

    private int number_table;

    private String waiter_name;

    private String client_name;

    public Info(String ingredient_name, int quantity_on_hand, String dishes_name, int quantity_required, int number_table, String waiter_name, String client_name) {
        this.ingredient_name = ingredient_name;
        this.quantity_on_hand = quantity_on_hand;
        this.dishes_name = dishes_name;
        this.quantity_required = quantity_required;
        this.number_table = number_table;
        this.waiter_name = waiter_name;
        this.client_name = client_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getQuantity_on_hand() {
        return quantity_on_hand;
    }

    public void setQuantity_on_hand(int quantity_on_hand) {
        this.quantity_on_hand = quantity_on_hand;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public int getQuantity_required() {
        return quantity_required;
    }

    public void setQuantity_required(int quantity_required) {
        this.quantity_required = quantity_required;
    }

    public int getNumber_table() {
        return number_table;
    }

    public void setNumber_table(int number_table) {
        this.number_table = number_table;
    }

    public String getWaiter_name() {
        return waiter_name;
    }

    public void setWaiter_name(String waiter_name) {
        this.waiter_name = waiter_name;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
