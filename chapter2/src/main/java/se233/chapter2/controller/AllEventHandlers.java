package se233.chapter2.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import se233.chapter2.Launcher;
import se233.chapter2.model.Currency;
import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    // Add logger - 3/5
    public static Logger logger = LogManager.getLogger(AllEventHandlers.class);

    public static void onRefresh() {
        try {
            Launcher.refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onAdd() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code:");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();

            if (code.isPresent()) {
                List<Currency> currencyList = Launcher.getCurrencyList();
                // Modify to accept both lowercase and uppercase
                Currency c = new Currency(code.get().toUpperCase());
                // Modify the application to display historical exchange rate data for up to 30 days for each currency
                List<CurrencyEntity> cList = FetchData.fetchRange(Launcher.getBase(), c.getShortCode(), 30);
                c.setHistorical(cList);
                c.setCurrent(cList.get(cList.size() - 1));
                currencyList.add(c);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
                // Add logger - 4/5
                logger.info("{} added", c.getShortCode());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            // Implement error handling to notify the user if an invalid currency short code is entered - START
        } catch (JSONException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid currency code please try again.");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.showAndWait();
            // Implement error handling to notify the user if an invalid currency short code is entered - END
        } catch (IndexOutOfBoundsException e) {}
    }

    public static void onDelete(String code) {
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;

            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                currencyList.remove(index);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
            // Add logger - 5/5
            logger.info("{} deleted", code);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void onWatch(String code) {
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                if (retrievedRate.isPresent()) {
                    double rate = Double.parseDouble(retrievedRate.get());
                    currencyList.get(index).setWatch(true);
                    currencyList.get(index).setWatchRate(rate);
                    Launcher.setCurrencyList(currencyList);
                    Launcher.refreshPane();
                }
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Add unwatch button - START
    public static void onUnwatch(String code) {
        try {
            List<Currency> currencyList = Launcher.getCurrencyList();
            int index = -1;
            for (int i = 0; i < currencyList.size(); i++) {
                if (currencyList.get(i).getShortCode().equals(code)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                currencyList.get(index).setWatch(false);
                currencyList.get(index).setWatchRate(0.0);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    // Add unwatch button - END

    // Design and implement components that enable users to configure the base currency - START
    public static void onConfig() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Config Base currency");
            dialog.setContentText("Base currency:");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if (code.isPresent()) {
                Launcher.setBase(code.get().toUpperCase());
                ArrayList<Currency> currencyList = new ArrayList<>();
                for (Currency c : Launcher.getCurrencyList()) {
                    List<CurrencyEntity> cList = FetchData.fetchRange(Launcher.getBase(), c.getShortCode(), 30);
                    c.setHistorical(cList);
                    c.setCurrent(cList.get(cList.size() - 1));
                    currencyList.add(c);
                }
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {}
    }
    // Design and implement components that enable users to configure the base currency - END
}
