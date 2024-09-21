module se233.chapter1 {
    requires javafx.controls;
    requires javafx.fxml;
    // Add logger - 2/5
    requires org.apache.logging.log4j;


    opens se233.chapter1 to javafx.fxml;
    exports se233.chapter1;
}