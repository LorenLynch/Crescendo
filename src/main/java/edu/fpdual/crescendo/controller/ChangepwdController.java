package edu.fpdual.crescendo.controller;

import edu.fpdual.crescendo.App;
import edu.fpdual.crescendo.model.manager.impl.UsersManagerImpl;
import edu.fpdual.crescendo.service.UsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ChangepwdController extends Switchers{

    UsersService usersService;
    public ChangepwdController() {
        this.usersService = new UsersService(new UsersManagerImpl());
    }

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    public void validateAndChange(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        String name = username.getText();
        String correo = email.getText();
        String pwd = password.getText();

        if (usersService.updatePassword(name,correo,pwd) && passwordFormat(pwd)){
            this.switchToLogin();
        }else{
            App.setRoot("Changepwd");
        }
    }

    //comprobamos que la contraseña tenga mas de 4 caracteres y al menos un número
    public boolean passwordFormat(String password){
        if (password.length() >= 4 && comprobarNumeros(password)){
            return true;
        }else{
            return false;
        }
    }

    private boolean comprobarNumeros(String password){
        boolean contieneNum = false;;
        for(int i=0 ; i<password.length() ; i++){
            if(password.charAt(i)>47 && password.charAt(i)<58){
                contieneNum = true;
                i = password.length();
            }
        }
        return contieneNum;
    }
}
