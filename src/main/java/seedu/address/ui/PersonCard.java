package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label studentId;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private FlowPane groups;
    @FXML
    private Label grade;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        studentId.setText(person.getStudentId().id);
        name.setText(person.getName().fullName);
        grade.setText(person.getGrade().value);
        email.setText(person.getEmail().value);
        person.getGroups().stream()
                .sorted(Comparator.comparing(group -> group.groupName))
                .forEach(group -> groups.getChildren().add(new Label(group.groupName)));

        // Add event handler to the email label
        email.setOnMouseClicked(event -> handleEmailClicked());
    }

    /*
     * Event handler for mouse clicking on email
     */
    private void handleEmailClicked() {
        String emailAddress = email.getText();
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            try {
                Desktop.getDesktop().mail(new URI("mailto:" + emailAddress));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                // Handle the exception
                showEmailErrorAlert();
            }
        } else {
            // Desktop or mail action is not supported
            showEmailErrorAlert();
        }
    }

    private void showEmailErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Unable to open email client. Please make sure you have an email client configured.");
        alert.showAndWait();
    }
}
