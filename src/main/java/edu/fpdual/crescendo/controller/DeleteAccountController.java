package edu.fpdual.crescendo.controller;

import edu.fpdual.crescendo.model.manager.impl.UsersManagerImpl;
import edu.fpdual.crescendo.service.UsersService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccountController extends Switchers{
    UsersService usersService;
    public DeleteAccountController() {
        this.usersService = new UsersService(new UsersManagerImpl());
    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void deleteAccount() throws SQLException, ClassNotFoundException, IOException {
        String name = username.getText();
        String pwd = password.getText();

        if (usersService.delete(name, pwd)) {
            this.switchToLogin();
        }else{
            System.out.println("No se ha podido eliminar la cuenta");
        }
    }
}
