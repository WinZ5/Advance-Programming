package se233.chapter2.controller.draw;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se233.chapter2.controller.AllEventHandlers;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

// Convert the two sub-panes in the CurrencyPane class into Callable objects - START
public class DrawTopPane extends FlowPane implements Callable<FlowPane> {
    private Button refresh;
    private Button add;
    private Button config;
    private Label update;

    public DrawTopPane() {
        refresh = new Button("Refresh");
        add = new Button("Add");
        // Design and implement components that enable users to configure the base currency
        config = new Button("Config");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onRefresh();
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.onAdd();
            }
        });
        // Design and implement components that enable users to configure the base currency - START
        config.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllEventHandlers.onConfig();
            }
        });
        // Design and implement components that enable users to configure the base currency - END
        update = new Label();
        update.setText(String.format("Last update: %s", LocalDateTime.now()));
    }

    @Override
    public FlowPane call() throws Exception {
        FlowPane  topPane = new FlowPane();
        topPane.setPadding(new Insets(10));
        topPane.setHgap(10);
        topPane.setPrefSize(640, 20);
        // Design and implement components that enable users to configure the base currency
        topPane.getChildren().addAll(refresh, update, add, config);

        return topPane;
    }
}
// Convert the two sub-panes in the CurrencyPane class into Callable objects - END
