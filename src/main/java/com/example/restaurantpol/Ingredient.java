package com.example.restaurantpol;

public class Ingredient {
    private int quantity_on_hand;
    private int ingredient_id;
    private String ingredient_name;
    private String unit_of_measurement;

    public Ingredient(int id,String name,String unit,int quantity){
        this.ingredient_id = id;
        this.ingredient_name = name;
        this.unit_of_measurement = unit;
        this.quantity_on_hand = quantity;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public void setUnit_of_measurement(String unit_of_measurement) {
        this.unit_of_measurement = unit_of_measurement;
    }

    public int getQuantity_on_hand() {
        return quantity_on_hand;
    }

    public void setQuantity_on_hand(int quantity_on_hand) {
        this.quantity_on_hand = quantity_on_hand;
    }
}
