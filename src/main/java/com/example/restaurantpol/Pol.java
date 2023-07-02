package com.example.restaurantpol;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pol {

    @FXML
    private TextField fieldlogin;

    @FXML
    private TextField fieldpassword;

    @FXML
    private Button buttonvxod;

    @FXML
    private Button buttonregister;

    @FXML
    private Button buttonchange;

    @FXML
    void pol() {
        buttonvxod.setOnAction(actionEvent -> {
            String login = fieldlogin.getText().trim();
            String password = fieldpassword.getText().trim();
            if (!login.equals("") && !password.equals("")) {
                loginuser(login, password);
            }
        });
        buttonregister.setOnAction(actionEvent -> {
            buttonregister.getScene().getWindow().hide();
            FXMLLoader vxod = new FXMLLoader();
            vxod.setLocation(getClass().getResource("pol2.fxml"));
            try {
                vxod.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent polina = vxod.getRoot();
            Stage register = new Stage();
            register.setScene(new Scene(polina));
            register.showAndWait();
        });
    }

    private void loginuser(String login, String password) {
        try {
            Connection connection = null;
            try {
                sql bd = sql.getInstance();
                connection = bd.getConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            register user = new register();
            user.setUsername(login);
            ResultSet rez = vxod.getuser(user);
            int k = 0;
            try {
                while (rez.next()) {
                    k++;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (k == 1) {
                buttonvxod.setOnAction(actionEvent -> {
                    buttonvxod.getScene().getWindow().hide();
                    FXMLLoader vxod = new FXMLLoader();
                    vxod.setLocation(getClass().getResource("pol1.fxml"));
                    Pol1.check();
                    try {
                        vxod.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent polina = vxod.getRoot();
                    Stage register = new Stage();
                    register.setScene(new Scene(polina));
                    register.showAndWait();
                });
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}