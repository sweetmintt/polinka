package com.example.restaurantpol;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dobavlenie {
    public static void regist(register user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Staff(username,password,access) VALUES (?,?,?)";
        try {
            Connection connection = null;
            try {
                sql bd = sql.getInstance();
                connection = bd.getConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            PreparedStatement bd = connection.prepareStatement(query);
            String hashedPassword = hashPassword(user.getPassword());
            bd.setString(1, user.getUsername());
            bd.setString(2, hashedPassword);
            bd.setString(3, user.getAccess());
            bd.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected static String hashPassword(String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
