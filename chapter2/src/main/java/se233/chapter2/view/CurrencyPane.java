package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se233.chapter2.controller.draw.DrawCurrencyInfoTask;
import se233.chapter2.controller.draw.DrawGraphTask;
import se233.chapter2.controller.draw.DrawTopAreaTask;
import se233.chapter2.model.Currency;

import java.util.concurrent.*;

// Convert the two sub-panes in the CurrencyPane class into Callable objects - START
public class CurrencyPane extends BorderPane {
    private Currency currency;

    public CurrencyPane(Currency currency) {
        this.setPadding(new Insets(0));
        this.setPrefSize(640, 300);
        this.setStyle("-fx-border-color: black");
        try {
            this.refreshPane(currency);
        } catch (ExecutionException e) {
            System.out.println("Encountered an execution exception.");
        } catch (InterruptedException e) {
            System.out.println("Encountered an interrupt exception.");
        }
    }

    public void refreshPane(Currency currency) throws ExecutionException, InterruptedException {
        this.currency = currency;
        FutureTask currencyInfoTask = new FutureTask<VBox>(new DrawCurrencyInfoTask(currency));
        FutureTask graphTask = new FutureTask<VBox>(new DrawGraphTask(currency));
        FutureTask topAreaTask = new FutureTask<HBox>(new DrawTopAreaTask(currency));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(currencyInfoTask);
        executor.execute(graphTask);
        executor.execute(topAreaTask);
        VBox currencyInfo = (VBox) currencyInfoTask.get();
        VBox currencyGraph = (VBox) graphTask.get();
        HBox topArea = (HBox) topAreaTask.get();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
        this.setCenter(currencyGraph);
    }
}
// Convert the two sub-panes in the CurrencyPane class into Callable objects - END
