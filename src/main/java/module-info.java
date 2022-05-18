module edu.fpdual.crescendo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.fpdual.crescendo to javafx.fxml;
    exports edu.fpdual.crescendo;
    opens edu.fpdual.crescendo.controller to javafx.fxml;
    exports edu.fpdual.crescendo.controller;
}