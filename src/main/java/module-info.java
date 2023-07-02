module com.example.restaurantpol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.restaurantpol to javafx.fxml;
    exports com.example.restaurantpol;
}