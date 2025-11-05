module com.robthelouvre.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.robthelouvre.ui to javafx.fxml;
    exports com.robthelouvre.ui;
}