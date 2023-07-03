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
    void pol() {
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
            sql sql = com.example.restaurantpol.sql.getInstance();
            register user = new register();
            user.setUsername(login);
            ResultSet rez = sql.getuser(user);
            int k = 0;
            try {
                while (rez.next()) {
                    k++;
                }
                if (k == 1) {
                    new vxod();
                }
            } finally {
                rez.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void vxod() {
        buttonvxod.setOnAction(actionEvent -> {
            String login = fieldlogin.getText().trim();
            String password = fieldpassword.getText().trim();
            if (!login.equals("") && !password.equals("")) {
                loginuser(login, password);
            }
            buttonvxod.getScene().getWindow().hide();
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(getClass().getResource("pol1.fxml"));
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
/*package com.example.restaurantpol;
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
            sql sql = new sql();
            register user = new register();
            user.setUsername(login);
            ResultSet rez = sql.getuser(user);
            int k = 0;
            try {
                while (rez.next()) {
                    k++;
                }
                if (k == 1) {
                    new vxod();
                }
            } finally {
                rez.close();
            }
            //sql.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void vxod() {
       buttonvxod.setOnAction(actionEvent -> {
            String logi1n = fieldlogin.getText().trim();
            String passwor1d = fieldpassword.getText().trim();
            if (!logi1n.equals("") && !passwor1d.equals("")) {
                loginuser(logi1n, passwor1d);
            }
            buttonvxod.getScene().getWindow().hide();
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(getClass().getResource("pol1.fxml"));
            //Pol1.check();
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
 */