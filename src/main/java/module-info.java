module com.lawajo.arkanoid {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.lawajo.arkanoid to javafx.fxml;
    exports com.lawajo.arkanoid;
}
