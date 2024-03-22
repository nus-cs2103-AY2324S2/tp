package seedu.address.ui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ConfirmationDialogBox {

    public void showConfirmationDialog() {
        Platform.runLater(() -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm");
            confirmationAlert.setHeaderText("Do you really want to delete?");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Logic to delete
                    System.out.println("Deletion confirmed");
                } else {
                    System.out.println("Deletion cancelled");
                }
            });
        });
    }
}

