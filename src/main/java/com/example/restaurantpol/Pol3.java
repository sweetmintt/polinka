package com.example.restaurantpol;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private static TextField fieldId;

    @FXML
    private static TextField fieldname;

    @FXML
    private static TextField fieldunit;

    @FXML
    private static TextField fieldquantity;

    /*public static TextField getFieldId() {
        return fieldId;
    }

    public static TextField getFieldname() {
        return fieldname;
    }

    public static TextField getFieldunit() {
        return fieldunit;
    }

    public static TextField getFieldquantity() {
        return fieldquantity;
    }*/
    ObservableList<Ingredient> list;
    @FXML
    public void initialize() {
        ingredient_id.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("ingredient_id"));
        ingredient_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredient_name"));
        unit_of_measurement.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("unit_of_measurement"));
        quantity_on_hand.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("quantity_on_hand"));
        list = sql.getIngredientsFromDatabase();
        table.setItems(list);
    }

    /*public void addIngredient(ActionEvent event) {
        buttonadd.setOnAction(ActionEvent -> {
            sql bd = null;
            try {
                bd = new sql();
                bd.addIngredients(fieldId.getText(), fieldname.getText(), fieldunit.getText(), fieldquantity.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }*/
}
