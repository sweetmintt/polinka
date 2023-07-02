package com.example.restaurantpol;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Pol2 {

    @FXML
    private TextField fieldlogin;

    @FXML
    private TextField fieldpassword;

    @FXML
    private Button buttonregister;

    @FXML
    private CheckBox buttonadmin;

    @FXML
    private CheckBox buttonuser;

    @FXML
    void register() {
        buttonregister.setOnAction(actionEvent -> {
            try {
                register1();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            buttonregister.getScene().getWindow().hide();
            FXMLLoader vxod = new FXMLLoader();
            vxod.setLocation(getClass().getResource("pol.fxml"));
            try {
                vxod.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent polina = vxod.getRoot();
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(polina));
            registerStage.showAndWait();
            registerStage.requestFocus();
        });
    }

    private void register1() throws SQLException, ClassNotFoundException {
        try {
            Connection connection = null;
            sql bd = null;
            try {
                bd = sql.getInstance();
                connection = bd.getConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String access = "";
            String username = fieldlogin.getText();
            String password = fieldpassword.getText();
            if (buttonadmin.isSelected())
                access = "Администратор";
            else
                access = "Пользователь";
            register user = new register(username, password, access);
            dobavlenie.regist(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}