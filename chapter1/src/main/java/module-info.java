module se223.chapter1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se223.chapter1 to javafx.fxml;
    exports se223.chapter1;
}