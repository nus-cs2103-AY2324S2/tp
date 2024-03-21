package seedu.address.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;

import seedu.address.model.contact.Contact;

public class MailApp {

    private Contact contact; // The contact associated with this MailApp

    /**
     * Initializes a new instance of the MailApp class with the provided contact object.
     * The contact object contains the contact information, including the email address,
     * to whom the email will be sent.
     *
     * @param contact The Contact object representing the contact information of the recipient.
     */
    public MailApp(Contact contact) {
        this.contact = contact;
    }


    /**
     * Handles the event when the email label is clicked. If the associated contact
     * has a valid email address, opens the default mail application with a new
     * email composition window addressed to the contact's email address.
     */
    @FXML
    public void handleEmailClicked() {
        if (contact != null && contact.getEmail() != null) {
            openDefaultMailApp(contact.getEmail().toString());
        }
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
