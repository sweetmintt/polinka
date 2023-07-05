package com.example.restaurantpol;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class Pol3 {
    @FXML
    private TableView<Ingredient> table;

    @FXML
    private  TableColumn<Ingredient, Integer> ingredient_id;

    @FXML
    private TableColumn<Ingredient, String> ingredient_name;

    @FXML
    private TableColumn<Ingredient, String> unit_of_measurement;

    @FXML
    private TableColumn<Ingredient, Integer> quantity_on_hand;

    @FXML
    private Button buttonadd;

    @FXML
    private Button buttonupdate;

    @FXML
    private Button buttondelete;

    @FXML
    private  TextField fieldId;

    @FXML
    private TextField fieldname;

    @FXML
    private TextField fieldunit;

    @FXML
    private TextField fieldquantity;

    ObservableList<Ingredient> list;
    @FXML
    public void initialize() {
        try {
            ingredient_id.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("ingredient_id"));
            ingredient_name.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("ingredient_name"));
            unit_of_measurement.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("unit_of_measurement"));
            quantity_on_hand.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("quantity_on_hand"));
            list = sql.getIngredientsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void addIngredient(ActionEvent event) {
        try {
            String id = fieldId.getText();
            String name = fieldname.getText();
            String unit = fieldunit.getText();
            String quantity = fieldquantity.getText();

            sql bd = new sql();
            bd.addIngredients(id, name, unit, quantity);

            // Обновляем данные в таблице
            list = sql.getIngredientsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateIngredient(ActionEvent event) {
        try {
            String id = fieldId.getText();
            String name = fieldname.getText();
            String unit = fieldunit.getText();
            String quantity = fieldquantity.getText();
            if (id.isEmpty()) {
                // Выводим сообщение об ошибке
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Поле ID не может быть пустым");
                alert.showAndWait();
                return; // Выходим из метода, чтобы предотвратить выполнение остального кода
            }

            sql bd = new sql();
            bd.updateIngredient(id, name, unit, quantity);

            // Обновляем данные в таблице
            list = sql.getIngredientsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteIngredient(ActionEvent event) {
        try {
            String id = fieldId.getText();

            sql bd = new sql();
            bd.deleteIngredient(id);

            // Обновляем данные в таблице
            list = sql.getIngredientsFromDatabase();
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
