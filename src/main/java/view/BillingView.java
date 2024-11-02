package view;

import db.DatabaseManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Product;

public class BillingView {
    private final VBox view;
    private final ListView<String> billingList;
    private final Label totalLabel;
    private double totalAmount = 0.0;

    public BillingView() {
        view = new VBox(10);
        Label title = new Label("Supermarket Billing System");

        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Scan barcode here...");

        barcodeField.setOnAction(e -> {
            handleBarcodeScan(barcodeField.getText());
            barcodeField.clear(); // Clear input for next scan
        });

        billingList = new ListView<>();
        totalLabel = new Label("Total: $0.00");

        view.getChildren().addAll(title, barcodeField, billingList, totalLabel);
    }

    private void handleBarcodeScan(String barcode) {
        Product product = DatabaseManager.getProductByBarcode(barcode);

        if (product != null) {
            addProductToBillingList(product);
            updateTotal(product.getPrice());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Product not found for barcode: " + barcode);
            alert.showAndWait();
        }
    }

    private void addProductToBillingList(Product product) {
        billingList.getItems().add(product.getName() + " - $" + product.getPrice());
    }

    private void updateTotal(double price) {
        totalAmount += price;
        totalLabel.setText("Total: $" + String.format("%.2f", totalAmount));
    }

    public VBox getView() {
        return view;
    }
}
