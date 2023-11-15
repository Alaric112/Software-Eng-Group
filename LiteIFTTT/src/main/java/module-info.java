module gruppo02 {
    requires javafx.controls;
    requires javafx.fxml;

    opens gruppo02 to javafx.fxml;
    exports gruppo02;
}
