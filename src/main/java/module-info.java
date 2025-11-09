module com.robthelouvre.ui {
    requires javafx.controls;
    requires javafx.fxml;
 //   requires com.robthelouvre.ui;


    opens com.robthelouvre.ui to javafx.fxml;
    exports com.robthelouvre.ui;
}