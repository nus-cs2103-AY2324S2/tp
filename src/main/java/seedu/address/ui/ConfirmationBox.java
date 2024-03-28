package seedu.address.ui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * The UI component that is responsible for asking for confirmation from users.
 */
public class ConfirmationBox implements Prompt {
    /**
     * Displays a modal confirmation dialog box with a specified title and message. The method blocks until the user
     * responds to the dialog.
     *
     * @param title The title of the confirmation dialog box. This appears in the title bar of the dialog.
     * @param message The message displayed to the user, asking for confirmation.
     * @return {@code true} if the user clicks the OK button, {@code false} if the user clicks the Cancel button
     *         or closes the dialog box.
     */
    @Override
    public boolean display(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}


