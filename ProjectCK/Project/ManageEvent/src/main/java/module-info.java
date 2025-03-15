module com.cnatro.manageevent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires java.base;
            
    opens com.cnatro.manageevent to javafx.fxml;
    opens com.cnatro.controller to javafx.fxml; 
    exports com.cnatro.manageevent;
}
