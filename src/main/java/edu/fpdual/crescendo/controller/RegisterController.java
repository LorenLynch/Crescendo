package edu.fpdual.crescendo.controller;

import edu.fpdual.crescendo.model.dao.Usuario;
import edu.fpdual.crescendo.model.manager.impl.UsersManagerImpl;
import edu.fpdual.crescendo.service.UsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterController extends Switchers{

    UsersService usersService;

    public RegisterController() {
        this.usersService = new UsersService(new UsersManagerImpl());
    }

    //Componentes de mi interfaz
    @FXML
    private TextField emailfxml;
    @FXML
    private DatePicker datefxml;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;


    //En este método se comprueba que el usuario introducido existe y se dirige al index
    @FXML
    private void validateAndRegister(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //Creamos las variables que recogen los datos introducidos en la ventana emergente
        String username = Username.getText();
        String password = Password.getText();
        String email = emailfxml.getText();
        LocalDate date = datefxml.getValue();

        //Creamos un usuario y le asignamos esos datos
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setFechaNacimiento(Date.valueOf(date));

        //Si la contraseña cumple con el formato deseado, se registra al usuario en la base de datos
       if(passwordFormat(password)){
           usersService.create(usuario);
           System.out.println("Se ha registrado correctamente");
           this.switchToLogin();
       }else {
           System.out.println("Hay algún campo incorrecto :(");
           System.out.println("Recuerde: El usuario debe tener una longitud superior a 4 caracteres");
           System.out.println("La contraseña debe contener al menos un número");
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
