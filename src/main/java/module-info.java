module com.lawajo.arkanoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.lawajo.arkanoid to javafx.fxml;
    exports com.lawajo.arkanoid;
}
