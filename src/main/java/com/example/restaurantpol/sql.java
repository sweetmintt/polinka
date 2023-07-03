package com.example.restaurantpol;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sql {
    private static sql instance;
    private static Connection connection;

    protected sql() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2282_restaurant", "std_2282_restaurant", "polina2810");
    }

    public static sql getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) { //если объект еще не создан
            instance = new sql(); //создать новый объект
        }
        return instance; //вернуть ранее созданный объект
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ResultSet getuser(register user) {
        ResultSet get = null;
        String query2 = "SELECT * FROM Staff WHERE username=?";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setString(1, user.getUsername());
            get = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return get;
    }



    public static void regist(register user) throws SQLException, ClassNotFoundException {
        if (checkUserExistence(user.getUsername())) {
            System.out.println("Пользователь уже существует");
        } else {
            String query1 = "INSERT INTO Staff(username,password,access) VALUES (?,?,?)";
            try (Connection connection = sql.getInstance().getConnection();
                 PreparedStatement stat = connection.prepareStatement(query1)) {
                String hashedPassword = dobavlenie.hashPassword(user.getPassword());
                stat.setString(1, user.getUsername());
                stat.setString(2, hashedPassword);
                stat.setString(3, user.getAccess());
                stat.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static ObservableList<Ingredient> getIngredientsFromDatabase() {
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        String query3 = "SELECT * FROM Ingredients";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query3);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int ingredientId = rs.getInt("ingredient_id");
                String ingredientName = rs.getString("ingredient_name");
                String unitOfMeasurement = rs.getString("unit_of_measurement");
                int quantityOnHand = rs.getInt("quantity_on_hand");
                Ingredient ingredient = new Ingredient(ingredientId, ingredientName, unitOfMeasurement, quantityOnHand);
                ingredients.add(ingredient);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }
    public static ObservableList<Meals> getMealsFromDatabase() {
        ObservableList<Meals> meals = FXCollections.observableArrayList();
        String query3 = "SELECT d.dish_name, i.ingredient_name, di.quantity_required FROM Dishes d JOIN Dish_Ingredients di ON d.dish_id = di.dish_id JOIN Ingredients i ON di.ingredient_id = i.ingredient_id;";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query3);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                String dishName = rs.getString("dish_name");
                String ingredientName = rs.getString("ingredient_name");
                int quantity = rs.getInt("quantity_required");
                Meals meal = new Meals(dishName, ingredientName, quantity);
                meals.add(meal);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return meals;
    }
    public void addIngredients(String ingredientId, String ingredientName, String unitOfMeasurement, String quantityOnHand) {
        String query4 = "INSERT INTO Ingredients(ingredient_id,ingredient_name,unit_of_measurement,quantity_on_hand) VALUES (?,?,?,?)";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query4);
            stat.setString(1, ingredientId);
            stat.setString(2, ingredientName);
            stat.setString(3, unitOfMeasurement);
            stat.setString(4, quantityOnHand);
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkUserExistence(String username) {
        String query = "SELECT * FROM Staff WHERE username=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Если есть хотя бы одна запись, значит, пользователь уже существует
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса", e);
        }
    }
    public void close() throws SQLException {
        connection.close();
    }
}
