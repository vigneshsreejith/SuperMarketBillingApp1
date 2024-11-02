package view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InventoryView {
    private final VBox view;
    private final TextField nameField;
    private final TextField priceField;
    private final TextField quantityField;
    private final TextField barcodeField;
    private final ListView<String> inventoryList;

    public InventoryView() {
        view = new VBox(10);
        Label title = new Label("Inventory Management");

        nameField = new TextField();
        nameField.setPromptText("Product Name");

        priceField = new TextField();
        priceField.setPromptText("Product Price");

        quantityField = new TextField();
        quantityField.setPromptText("Product Quantity");

        barcodeField = new TextField();
        barcodeField.setPromptText("Barcode");

        Button addButton = new Button("Add Product");
        addButton.setOnAction(e -> addProduct());

        inventoryList = new ListView<>();

        view.getChildren().addAll(title, nameField, priceField, quantityField, barcodeField, addButton, inventoryList);

        loadInventory();
    }

    private void loadInventory() {
        inventoryList.getItems().clear();
        // This method can be implemented to fetch and display existing products
    }

    private void addProduct() {
        String name = nameField.getText();
        String priceStr = priceField.getText();
        String quantityStr = quantityField.getText();
        String barcode = barcodeField.getText();

        if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty() || barcode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all fields!");
            alert.showAndWait();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            // Here, add the logic to save the product to the database

            inventoryList.getItems().add(name + " - $" + price + " (Qty: " + quantity + ")");
            nameField.clear();
            priceField.clear();
            quantityField.clear();
            barcodeField.clear();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number format.");
            alert.showAndWait();
        }
    }

    public VBox getView() {
        return view;
    }
}
