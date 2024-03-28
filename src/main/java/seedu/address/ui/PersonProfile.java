package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays profile of a {@code Person}.
 */
public class PersonProfile extends UiPart<Region> {

    private static final String FXML = "PersonProfile.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;

    @FXML
    private VBox profilePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label upcoming;
    @FXML
    private Label lastcontact;

    /**
     * Creates a {@code PersonProfile}.
     */
    public PersonProfile() {
        super(FXML);
        profilePane.setVisible(false);
    }

    /**
     * Creates a {@code PersonProfile} with the given {@code Person}.
     */
    public PersonProfile(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText("Phone number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        setOptionalFields();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        profilePane.setVisible(true);
    }

    /**
     * Updates the {@code PersonProfile} with the given {@code Person}.
     */
    public void setPerson(Person person) {
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText("Phone number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        setOptionalFields();
        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        profilePane.setVisible(true);
    }

    private void setOptionalFields() {
        if (person.hasUpcoming()) {
            upcoming.setVisible(true);
            upcoming.setManaged(true);
            upcoming.setText("Upcoming: " + person.getUpcoming().toString());
        } else {
            upcoming.setVisible(false);
            upcoming.setManaged(false);
        }

        if (person.hasLastcontact()) {
            lastcontact.setVisible(true);
            lastcontact.setManaged(true);
            lastcontact.setText("Last contacted: " + person.getLastcontact().toString());
        } else {
            lastcontact.setVisible(false);
            lastcontact.setManaged(false);
        }
    }
}
