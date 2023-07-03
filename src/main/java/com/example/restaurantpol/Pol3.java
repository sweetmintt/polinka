package com.example.restaurantpol;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Pol3 {
    @FXML
    protected TableView<Ingredient> table;

    @FXML
    private TableColumn<Ingredient, Integer> ingredient_id;

    @FXML
    private TableColumn<Ingredient, String> ingredient_name;

    @FXML
    private TableColumn<Ingredient, String> unit_of_measurement;

    @FXML
    private TableColumn<Ingredient, Integer> quantity_on_hand;
    ObservableList<Ingredient> list;
    @FXML
    private Button buttonadd;

    @FXML
    private Button buttonupdate;

    @FXML
    private Button buttondelete;

    @FXML
    protected static TextField fieldid;

    @FXML
    protected static TextField fieldname;

    @FXML
    protected static TextField fieldunit;

    @FXML
    protected static TextField fieldquantity;

    @FXML
    public void addIngredients(String ingredientId, String ingredientName, String unitOfMeasurement, String quantityOnHand){
        ingredientId = fieldid.getText();
        ingredientName = fieldname.getText();
        unitOfMeasurement = fieldunit.getText();
        quantityOnHand = fieldquantity.getText();
        addIngredients(ingredientId, ingredientName, unitOfMeasurement, quantityOnHand);
    }
    @FXML
    public void initialize() {
        ingredient_id.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("ingredient_id"));
        ingredient_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredient_name"));
        unit_of_measurement.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("unit_of_measurement"));
        quantity_on_hand.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("quantity_on_hand"));
        list = sql.getIngredientsFromDatabase();
        table.setItems(list);
    }
}