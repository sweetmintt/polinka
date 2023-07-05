package com.example.restaurantpol;

public class Meals {
    private String dishes_name;

    private int id;

    public Meals(int id,String dishes_name) {
        this.id = id;
        this.dishes_name = dishes_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

}
