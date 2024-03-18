package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.Person;

public class MailApp {

    private Person person;

    @FXML
    private Label emailLabel;

    @FXML
    public void handleEmailClicked() {
        if (person != null && person.getEmail() != null) {
            openDefaultMailApp(person.getEmail().toString());
        }
    }

    /**
     * Sets the person associated with this MailApp.
     *
     * @param person The person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

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
