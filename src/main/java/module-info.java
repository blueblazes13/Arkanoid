module com.lawajo.arkanoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.lawajo.arkanoid to javafx.fxml;
    exports com.lawajo.arkanoid;

    requires com.google.gson;
    opens com.lawajo.arkanoid.model to com.google.gson;
}
