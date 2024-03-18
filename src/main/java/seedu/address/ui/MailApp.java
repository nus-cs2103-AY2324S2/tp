package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.Person;

public class MailApp {

    private Person person; // The person associated with this MailApp

    @FXML
    private Label emailLabel; // JavaFX Label for displaying the email address

    /**
     * Handles the event when the email label is clicked. If the associated person
     * has a valid email address, opens the default mail application with a new
     * email composition window addressed to the person's email address.
     */
    @FXML
    public void handleEmailClicked() {
        if (person != null && person.getEmail() != null) {
            openDefaultMailApp(person.getEmail().toString());
        }
    }

    /**
     * Sets the person associated with this MailApp.
     *
     * @param person The person object to associate with this MailApp
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Opens the default mail application with a new email composition window
     * addressed to the specified recipient's email address.
     *
     * @param recipientEmail The email address of the recipient
     */
    private void openDefaultMailApp(String recipientEmail) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            try {
                URI mailto = new URI("mailto:" + recipientEmail);
                Desktop.getDesktop().mail(mailto);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Desktop doesn't support mailto");
        }
    }
}
