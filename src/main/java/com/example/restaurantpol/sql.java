package com.example.restaurantpol;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sql {
    private static sql instance;
    private Connection connection;

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

    public Connection getConnection() {
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
        String query1 = "INSERT INTO Staff(username,password,access) VALUES (?,?,?)";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query1);
            String hashedPassword = dobavlenie.hashPassword(user.getPassword());
            stat.setString(1, user.getUsername());
            stat.setString(2, hashedPassword);
            stat.setString(3, user.getAccess());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void close() throws SQLException {
        connection.close();
    }
}
