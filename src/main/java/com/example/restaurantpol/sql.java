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
    public ResultSet getAccess(register user){
        ResultSet get = null;
        String query2 = "SELECT * FROM Staff WHERE username=?";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setString(1, user.getAccess());
            get = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return get;
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
    public void updateUser(String password, String newName, String newAccess) throws SQLException {
        String query = "UPDATE Staff SET password = ?, access = ? WHERE username = ?";
        register user = new register();
        PreparedStatement statement = connection.prepareStatement(query);
        String hashedPassword = dobavlenie.hashPassword(user.getPassword());
        statement.setString(1, newName);
        statement.setString(2, hashedPassword);
        statement.setString(3, newAccess);
        statement.executeUpdate();
    }
    /*public void addIngredients(String ingredientId, String ingredientName, String unitOfMeasurement, String quantityOnHand) {
        String query = "INSERT INTO Ingredients (ingredient_id, ingredient_name, unit_of_measurement, quantity_on_hand) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, ingredientId);
            stat.setString(2, ingredientName);
            stat.setString(3, unitOfMeasurement);
            stat.setString(4, quantityOnHand);
            stat.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void editIngredients() {
        String p = Pol3.getFieldId().getText();
        String o = Pol3.getFieldname().getText();
        String l = Pol3.getFieldunit().getText();
        String i =Pol3.getFieldquantity().getText();
        String query7 = "UPDATE Ingredients set ingredient_id '"+p+"',ingredient_name'"+o+"',unit_of_measurement'"+l+"',quantity_on_hand'"+i+"'where ingrediend_id = '"+p+"' ";
        try {
            Connection connection = sql.getInstance().getConnection();
            PreparedStatement stat = connection.prepareStatement(query7);
            //Pol3.initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
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

    public void close() throws SQLException {
        connection.close();
    }
}
