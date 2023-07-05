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

    @FXML
    private Button buttoningridients;

    @FXML
    private Button buttonInfo;

    protected static register currentUser;

    @FXML
    void initialize() {
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

    @FXML
    void dish(ActionEvent event) {
            buttondishes.setOnAction(actionEvent -> {
                FXMLLoader vxod1 = new FXMLLoader();
                vxod1.setLocation(Pol.class.getResource("pol4.fxml"));
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

    @FXML
    void ingredient(ActionEvent event) {
        buttoningridients.setOnAction(actionEvent -> {
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(Pol.class.getResource("pol3.fxml"));
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
}

