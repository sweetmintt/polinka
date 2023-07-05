package com.example.restaurantpol;

import java.sql.SQLException;

public class Pol6Model {
    public void updateUser(String userName, String password, String newAccess) {
        try {
            sql sql = com.example.restaurantpol.sql.getInstance();
            sql.updateUser(userName, password, newAccess);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}