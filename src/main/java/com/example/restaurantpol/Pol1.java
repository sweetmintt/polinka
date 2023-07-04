package com.example.restaurantpol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pol1 {

    @FXML
    private Button buttondishes;
    protected static register currentUser;

    @FXML
    private Button buttoningridients;
    @FXML
    private Button buttonInfo;

    @FXML
    void butinfo(ActionEvent event) {
        buttonInfo.setOnAction(actionEvent -> {
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(Pol.class.getResource("pol5.fxml"));
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
    }

    public static void check(String access) {
        try {
            sql sql = com.example.restaurantpol.sql.getInstance();
            register user = new register();
            user.setAccess(access);
            ResultSet rez = com.example.restaurantpol.sql.getInstance().getAccess(user);
            if (register.getAccess() != null && register.getAccess().equals("Пользователь")) {
                //buttoningridients.setVisible(false);
            } else {
                //buttoningridients.setVisible(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}