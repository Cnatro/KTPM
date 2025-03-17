module com.cnatro.fxenglishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.cnatro.fxenglishapp to javafx.fxml;
    opens com.cnatro.pojo to javafx.base;
    exports com.cnatro.fxenglishapp;
}
