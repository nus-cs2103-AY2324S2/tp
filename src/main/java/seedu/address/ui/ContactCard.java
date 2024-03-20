package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.MailApp;
import seedu.address.model.contact.Contact;

/**
 * An UI component that displays information of a {@code Contact}.
 */
public class ContactCard extends UiPart<Region> {

    private static final String FXML = "ContactListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CodeConnect level 4</a>
     */

    public final Contact contact;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label emailLabel;
    @FXML
    private Label email;
    @FXML
    private Label githubUsername;
    @FXML
    private HBox techStacks;
    @FXML
    private HBox tags;

    /**
     * Creates a {@code ContactCode} with the given {@code Contact} and index to display.
     */
    public ContactCard(Contact contact, int displayedIndex) {
        super(FXML);
        this.contact = contact;
        id.setText(displayedIndex + ". ");
        name.setText(contact.getName().fullName);
        phone.setText("Phone: " + contact.getPhone().value);
        address.setText("Address: " + contact.getAddress().value);
        emailLabel.setText("Email: ");
        email.setText(contact.getEmail().value);
        //email.setText("Email: " + contact.getEmail().value);
        githubUsername.setText("@"+ contact.getGitHubUsername().username);
        contact.getTechStack().stream()
                .sorted(Comparator.comparing(techStack -> techStack.techStackName))
                .forEach(techStack -> techStacks.getChildren().add(new Label(techStack.techStackName)));

        contact.getTags().stream()
                .sorted(Comparator.comparing(tag-> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        email.setOnMouseClicked(event -> {
            MailApp mailApp = new MailApp(contact);
            mailApp.handleEmailClicked();
        });
    }
}

