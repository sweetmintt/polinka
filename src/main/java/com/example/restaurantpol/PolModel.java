package com.example.restaurantpol;

import java.sql.SQLException;

public class PolModel {
    private Pol polController;
    private sql sqlManager;

    public PolModel(Pol polController) throws SQLException, ClassNotFoundException {
        this.polController = polController;
        sqlManager = new sql();
    }

    public void loginuser(String login, String password) {
        try {
            sql bd = new sql();
            if (sqlManager.checkUserExistence(login)) {
                System.out.println("Вход прошел успешно");
                if (sql.loginuser1(login,password)) {
                    polController.openPol1Window();
                }
                else { polController.openPol7Window();}
            } else {
                System.out.println("Пользователь не найден");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}