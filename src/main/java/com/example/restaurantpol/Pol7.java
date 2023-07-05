package com.example.restaurantpol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Pol7 {

    @FXML
    private Button buttonInfo;

    @FXML
    void initialize(ActionEvent event) {
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
}
