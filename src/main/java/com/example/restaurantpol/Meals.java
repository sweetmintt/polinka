package com.example.restaurantpol;

public class Meals {
    private String ingredient_name;
    private String dishes_name;
    private int quantity_required;

    public Meals(String dishes_name,String ingredient_name,int quantity_required) {
        this.dishes_name = dishes_name;
        this.ingredient_name = ingredient_name;
        this.quantity_required = quantity_required;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getQuantity_required() {
        return quantity_required;
    }

    public void setQuantity_required(int quantity_required) {
        this.quantity_required = quantity_required;
    }
}
