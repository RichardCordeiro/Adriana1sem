module concord.tarefa3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens concord.tarefa3 to javafx.fxml;
    opens concord.tarefa3.controllers to javafx.fxml;
    exports concord.tarefa3;
    exports concord.tarefa3.dao;
    exports concord.tarefa3.database;
    exports concord.tarefa3.models;
    exports concord.tarefa3.controllers;
}