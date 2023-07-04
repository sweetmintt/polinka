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
            System.out.println("Регистрация прошла успешна");
            try {
                register1();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void register1() throws SQLException, ClassNotFoundException {
        try {
            sql bd = sql.getInstance();
            String access = "";
            String username = fieldlogin.getText();
            String password = fieldpassword.getText();
            if (buttonadmin.isSelected()) {
                access = "Администратор";
            } else {
                access = "Пользователь";
            }
            register user = new register(username, password, access);
            bd.regist(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
