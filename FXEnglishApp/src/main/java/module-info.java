module com.cnatro.fxenglishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.cnatro.fxenglishapp to javafx.fxml;
    exports com.cnatro.fxenglishapp;
}
