package com.example.restaurantpol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vxod {
    public static ResultSet getuser(register user) {
        ResultSet get = null;
        String query1 = "SELECT * FROM Staff WHERE username=?";
        try {
            try {
                Connection connection = null;
                try {
                    sql bd = sql.getInstance();
                    connection = bd.getConnection();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                PreparedStatement bd = connection.prepareStatement(query1);
                bd.setString(1, user.getUsername());
                get = bd.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return get;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
