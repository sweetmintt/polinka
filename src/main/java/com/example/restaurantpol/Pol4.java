package com.example.restaurantpol;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Pol4 {

    @FXML
    private TableView<Meals> table;

    @FXML
    private TableColumn<Ingredient, Integer> dishes_name;

    @FXML
    private TableColumn<Ingredient, String> ingredient_name;

    @FXML
    private TableColumn<Ingredient, Integer> quantity_required;

    @FXML
    private Button buttonadd;

    @FXML
    private Button buttonupdate;

    @FXML
    private Button buttondelete;

    @FXML
    private TextField fdname;

    @FXML
    private TextField finame;

    @FXML
    private TextField fieldquantity;

    ObservableList<Meals> list;
    @FXML
    public void initialize() {
        dishes_name.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("dishes_name"));
        ingredient_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredient_name"));
        quantity_required.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("quantity_required"));
        list = sql.getMealsFromDatabase();
        table.setItems(list);
    }

}