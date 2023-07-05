package com.example.restaurantpol;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Pol6 {

    @FXML
    private TextField fieldlogin;

    @FXML
    private TextField fieldpassword;

    @FXML
    private Button buttonchange;
    @FXML
    private RadioButton admin;

    @FXML
    private RadioButton user;

    @FXML
    void initialize() {
        buttonchange.setOnAction(actionEvent -> {
            String access = "";
            if (admin.isSelected()) {
                access = "Администратор";
            } else {
                access = "Пользователь";
            }
            Pol6Model pol6Model = new Pol6Model();
            pol6Model.updateUser(fieldlogin.getText(), fieldpassword.getText(), access);
            System.out.println("Изменение данных прошло успешно");
        });
    }
}

