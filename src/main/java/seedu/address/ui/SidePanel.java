package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Github;
import seedu.address.model.person.Person;
import seedu.address.model.person.Telegram;

/**
 * Panel displaying the details of a selected person.
 */
public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SidePanel.class);

    @FXML
    private Label defaultMessageLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label classGroupLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label telegramLabel;
    @FXML
    private Label githubLabel;

    /**
     * Creates a {@code SidePanel}.
     */
    public SidePanel() {
        super(FXML);
        showDefaultMessage();
    }

    /**
     * Shows the default message and hides person details.
     */
    private void showDefaultMessage() {
        defaultMessageLabel.setVisible(true);
        nameLabel.setVisible(false);
        classGroupLabel.setVisible(false);
        emailLabel.setVisible(false);
        phoneLabel.setVisible(false);
        telegramLabel.setVisible(false);
        githubLabel.setVisible(false);
    }

    /**
     * Displays the details of a {@code Person}.
     *
     * @param person The person whose details are to be displayed.
     */
    public void displayPerson(Person person) {
        if (person != null) {
            nameLabel.setVisible(true);
            nameLabel.setText("Name: " + person.getName().fullName);
            classGroupLabel.setVisible(true);
            classGroupLabel.setText("Class Group: " + person.getClassGroup().toString());
            emailLabel.setVisible(true);
            emailLabel.setText("Email: " + person.getEmail().toString());
            phoneLabel.setVisible(true);
            phoneLabel.setText("Phone: " + person.getPhone().toString());
            telegramLabel.setVisible(true);
            telegramLabel.setText("Telegram: "
                + person.getTelegram().map(Telegram::toString).orElse("N/A"));
            githubLabel.setVisible(true);
            githubLabel.setText("GitHub: "
                + person.getGithub().map(Github::toString).orElse("N/A"));

            defaultMessageLabel.setVisible(false);
        } else {
            resetDetails();
        }
    }

    /**
     * Clears all the details from the side panel.
     */
    private void resetDetails() {
        nameLabel.setText("");
        classGroupLabel.setText("");
        emailLabel.setText("");
        phoneLabel.setText("");
        telegramLabel.setText("");
        githubLabel.setText("");

        nameLabel.setVisible(false);
        classGroupLabel.setVisible(false);
        emailLabel.setVisible(false);
        phoneLabel.setVisible(false);
        telegramLabel.setVisible(false);
        githubLabel.setVisible(false);

        showDefaultMessage();
    }
}
