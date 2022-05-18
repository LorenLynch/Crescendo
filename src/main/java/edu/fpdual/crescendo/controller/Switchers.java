package edu.fpdual.crescendo.controller;

import edu.fpdual.crescendo.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class Switchers {

    @FXML
    public void switchToLogin() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void switchToRegister() throws IOException {
        App.setRoot("Register");
    }

    @FXML
    public void switchToIndex() throws IOException{
        App.setRoot("Index");
    }

    @FXML
    public void switchToOptions() throws IOException {
        App.setRoot("Options");
    }

    @FXML
    public void switchToChangepwd() throws IOException {
        App.setRoot("Changepwd");
    }

    @FXML
    public void switchToDeleteAccount() throws IOException {
        App.setRoot("DeleteAccount");
    }
}
