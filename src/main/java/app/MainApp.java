package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.BillingView;
import view.InventoryView;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab billingTab = new Tab("Billing");
        billingTab.setContent(new BillingView().getView());

        Tab inventoryTab = new Tab("Inventory");
        inventoryTab.setContent(new InventoryView().getView());

        tabPane.getTabs().addAll(billingTab, inventoryTab);

        Scene scene = new Scene(tabPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Supermarket Billing Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
