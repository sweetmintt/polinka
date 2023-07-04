package com.example.restaurantpol;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Pol5 {

    @FXML
    private TableView<Info> table;

    @FXML
    private TableColumn<Ingredient, String> ingredient_name;

    @FXML
    private TableColumn<Ingredient, Integer> quantity_on_hand;

    @FXML
    private TableColumn<Ingredient, String> dishes_name;

    @FXML
    private TableColumn<Ingredient, Integer> quantity_required;

    @FXML
    private TableColumn<Ingredient, Integer> number_table;

    @FXML
    private TableColumn<Ingredient, String> waiter_name;

    @FXML
    private TableColumn<Ingredient, String> client_name;

    ObservableList<Info> list;
    @FXML
    public void initialize() {
        ingredient_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredient_name"));
        quantity_on_hand.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("quantity_on_hand"));
        dishes_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("dishes_name"));
        quantity_required.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("quantity_required"));
        number_table.setCellValueFactory(new PropertyValueFactory<Ingredient,Integer>("number_table"));
        waiter_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("waiter_name"));
        client_name.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("client_name"));
        list = sql.getInfoFromDatabase();
        table.setItems(list);
    }
}
