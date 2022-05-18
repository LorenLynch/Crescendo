package edu.fpdual.crescendo.controller;

import edu.fpdual.crescendo.model.dao.Usuario;
import edu.fpdual.crescendo.model.manager.impl.UsersManagerImpl;
import edu.fpdual.crescendo.service.UsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends Switchers{

    UsersService usersService;

    public LoginController() {
        this.usersService = new UsersService(new UsersManagerImpl());
    }

    //Componentes de mi interfaz

        @FXML
        private TextField Username;
        @FXML
        private PasswordField Password;

        //En este método se comprueba que el usuario introducido existe y se dirige al index
        @FXML
        private void validateAndLogIn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
            String username = Username.getText();
            String password = Password.getText();

            Usuario usuario = usersService.findByNameAndPass(username, password);
            if (usuario != null) {
                this.switchToIndex();
            } else {
                this.switchToLogin();
            }
        }



    }

