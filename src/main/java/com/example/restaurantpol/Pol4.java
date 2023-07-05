package com.example.restaurantpol;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class Pol4 {

    @FXML
    private TableView<Meals> table;

    @FXML
    private TableColumn<Meals, Integer> id;

    @FXML
    private TableColumn<Meals, String> dishes_name;

    @FXML
    private Button buttonadd;

    @FXML
    private Button buttonupdate;

    @FXML
    private Button buttondelete;

    @FXML
    private TextField fdname;

    @FXML
    private TextField fieldId;

    ObservableList<Meals> list;

    @FXML
    void addMeals(ActionEvent event) {
        try {
            int id= Integer.parseInt(fieldId.getText());
            String name = fdname.getText();
            sql bd = new sql();
            bd.addMeals(id, name);
            // Обновляем данные в таблице
            list = sql.getMealsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateMeals(ActionEvent event) {
        try {
            int id= Integer.parseInt(fieldId.getText());
            String name = fdname.getText();

            sql bd = new sql();
            bd.updateMeals(id,name);

            // Обновляем данные в таблице
            list = sql.getMealsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteMeals(ActionEvent event) {
        try {
            int id= Integer.parseInt(fieldId.getText());
            String name = fdname.getText();

            sql bd = new sql();
            bd.deleteMeals(id,name);

            // Обновляем данные в таблице
            list = sql.getMealsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Meals,Integer>("id"));
        dishes_name.setCellValueFactory(new PropertyValueFactory<Meals,String>("dishes_name"));
        list = sql.getMealsFromDatabase();
        table.setItems(list);
    }

}