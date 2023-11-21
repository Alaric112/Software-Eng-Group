module group2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens group2 to javafx.fxml;
    exports group2;
}
