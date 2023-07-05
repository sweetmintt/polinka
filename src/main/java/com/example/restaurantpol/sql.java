package com.example.restaurantpol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
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


    public static ResultSet getuser(register user) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Staff WHERE username=? AND password=?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        return statement.executeQuery();
    }
    public static boolean loginuser1(String login, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Staff WHERE username=? ";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String role = resultSet.getString("access");
            if (role.equals("Администратор")) {
                return true;
            }
        }
        return false;
    }
    public void regist(register user) throws SQLException, ClassNotFoundException {
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

    public static ObservableList<Ingredient> getIngredientsFromDatabase() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Ingredients";
        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        try {
            Connection connection = sql.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("ingredient_id");
                String name = resultSet.getString("ingredient_name");
                String unit = resultSet.getString("unit_of_measurement");
                int quantity = resultSet.getInt("quantity_on_hand");
                Ingredient ingredient = new Ingredient(id, name, unit, quantity);
                ingredients.add(ingredient);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    public static ObservableList<Meals> getMealsFromDatabase() {
        ObservableList<Meals> meals = FXCollections.observableArrayList();
        String query3 = "SELECT * FROM Dishes";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query3);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("dish_id");
                String dishName = rs.getString("dish_name");
                Meals meal = new Meals(id,dishName);
                meals.add(meal);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return meals;
    }
    public static ObservableList<Info> getInfoFromDatabase() {
        ObservableList<Info> info = FXCollections.observableArrayList();
        String query5 = "SELECT Ingredients.ingredient_name, Ingredients.quantity_on_hand, Dishes.dish_name, Dish_Ingredients.quantity_required, Tables.table_id, Waiters.waiter_name, Clients.client_name FROM Ingredients JOIN Dish_Ingredients ON Ingredients.ingredient_id = Dish_Ingredients.ingredient_id JOIN Dishes ON Dish_Ingredients.dish_id = Dishes.dish_id JOIN Meal_Dishes ON Dishes.dish_id = Meal_Dishes.dish_id JOIN Meals ON Meal_Dishes.meal_id = Meals.meal_id JOIN Tables ON Meals.table_id = Tables.table_id JOIN Waiters ON Meals.waiter_id = Waiters.waiter_id JOIN Clients ON Meals.client_id = Clients.client_id";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query5);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                String ingredientName = rs.getString("ingredient_name");
                int quantity_on_hand = rs.getInt("quantity_on_hand");
                String dishName = rs.getString("dish_name");
                int quantity_required = rs.getInt("quantity_required");
                int table_id = rs.getInt("table_id");
                String waiterName = rs.getString("waiter_name");
                String clientName = rs.getString("client_name");
                Info info1 = new Info(ingredientName,quantity_on_hand,dishName, quantity_required,table_id,waiterName,clientName);
                info.add(info1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return info;
    }
    public void updateUser(String login, String password, String access) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Staff SET password = ?, access = ? WHERE username = ?");
            String hashedPassword = dobavlenie.hashPassword(password);
            stmt.setString(1, hashedPassword);
            stmt.setString(2, access);
            stmt.setString(3, login);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            conn.close();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredients(String id, String name, String unit, String quantity) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Ingredients (ingredient_id, ingredient_name, unit_of_measurement, quantity_on_hand) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, unit);
            statement.setString(4, quantity);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateIngredient(String id, String name, String unit, String quantity) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Ingredients SET ingredient_name = ?, unit_of_measurement = ?, quantity_on_hand = ? WHERE ingredient_id = ?";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, unit);
            statement.setString(3, quantity);
            statement.setString(4, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteIngredient(String id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM Ingredients WHERE ingredient_id = ?";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkUserExistence(String login) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Staff WHERE username=?";
        Connection connection = com.example.restaurantpol.sql.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        int k = 0;
        if (resultSet.next()){k++;}
        if (k == 1) return true;
        else return false;
    }
    public void addMeals(int id, String dishName) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Dishes (dish_id, dish_name) VALUES (?, ?)";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, dishName);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateMeals(int id, String dishName) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Dishes SET dish_name = ? WHERE dish_id = ?");
        stmt.setString(1, dishName);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
    public void deleteMeals(int id,String name) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Dishes WHERE dish_id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
