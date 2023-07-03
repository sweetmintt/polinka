package com.example.restaurantpol;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    void initialize() {
        buttonregister.setOnAction(actionEvent -> {
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(Pol.class.getResource("pol2.fxml"));
            try {
                vxod1.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent polina = vxod1.getRoot();
            Stage register = new Stage();
            register.setScene(new Scene(polina));
            register.showAndWait();
        });

        buttonvxod.setOnAction(actionEvent -> {
            String login = fieldlogin.getText().trim();
            String password = fieldpassword.getText().trim();
            if (!login.equals("") && !password.equals("")) {
                loginuser(login, password);
            }
        });
    }

    private void loginuser(String login, String password) {
        try {
            sql sql = com.example.restaurantpol.sql.getInstance();
            register user = new register();
            user.setUsername(login);
            ResultSet rez = com.example.restaurantpol.sql.getuser(user);
            int k = 0;
            try {
                while (rez.next()) {
                    k++;
                }
                if (k == 1) {
                    openNewWindow();
                }
            } finally {
                rez.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void openNewWindow() {
        FXMLLoader vxod1 = new FXMLLoader();
        vxod1.setLocation(Pol.class.getResource("pol1.fxml"));
        try {
            vxod1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent polina = vxod1.getRoot();
        Stage register = new Stage();
        register.setScene(new Scene(polina));
        register.showAndWait();
    }
}

