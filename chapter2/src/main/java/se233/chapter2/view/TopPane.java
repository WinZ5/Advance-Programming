package se233.chapter2.view;

import javafx.scene.layout.FlowPane;
import se233.chapter2.controller.draw.DrawTopPane;

import java.util.concurrent.*;

// Convert the two sub-panes in the CurrencyPane class into Callable objects - START
public class TopPane extends FlowPane {

    public TopPane() {
        try {
            this.refreshPane();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refreshPane() throws ExecutionException, InterruptedException {
        FutureTask topPane = new FutureTask(new DrawTopPane());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(topPane);
        FlowPane pane = (FlowPane) topPane.get();
        this.getChildren().clear();
        this.getChildren().add(pane);
    }
}
// Convert the two sub-panes in the CurrencyPane class into Callable objects - END
