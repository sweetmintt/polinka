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
    private Pol polController;
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
        polController = new Pol();
        buttonvxod.setOnAction(actionEvent -> {
            String login = fieldlogin.getText();
            String password = fieldpassword.getText();
            if (!login.equals("") && !password.equals("")) {
                PolModel polModel = null;
                try {
                    polModel = new PolModel(polController);
                    polModel.loginuser(login, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        buttonchange.setOnAction(actionEvent -> {
            FXMLLoader vxod1 = new FXMLLoader();
            vxod1.setLocation(Pol.class.getResource("pol6.fxml"));
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

    protected void openPol1Window() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pol1.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void openPol7Window() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pol7.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
