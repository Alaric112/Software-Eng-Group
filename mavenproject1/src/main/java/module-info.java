module group2.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens group2.mavenproject1 to javafx.fxml;
    exports group2.mavenproject1;
}
