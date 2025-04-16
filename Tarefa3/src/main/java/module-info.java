module concord.tarefa3 {
    requires javafx.controls;
    requires javafx.fxml;



    opens concord.tarefa3.controllers to javafx.fxml;
    exports concord.tarefa3;
    exports concord.tarefa3.models;
}