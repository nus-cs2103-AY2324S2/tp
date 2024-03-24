package seedu.teachstack.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.teachstack.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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

    @FXML
    private Label weakLabel;

    private DesktopInterface mockDesktop;

    private boolean emailAlertShown;

    private boolean isTestEnvironment = false;

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

        if (person.getGrade() != null && person.isWeak()) {
            weakLabel.setText("Weak");
            weakLabel.setTextFill(Color.RED);
        } else {
            weakLabel.setVisible(false); // If grade is not below threshold, don't display anything
        }

        person.getGroups().stream()
                .sorted(Comparator.comparing(group -> group.groupName))
                .forEach(group -> groups.getChildren().add(new Label(group.groupName)));

        // Add event handler to the email label
        email.setOnMouseClicked(event -> handleEmailClicked());
    }

    public void setTestEnvironment(boolean isTestEnvironment) {
        this.isTestEnvironment = isTestEnvironment;
    }

    /**
     * Event handler for mouse clicking on the email label.
     * If the Desktop is supported and the MAIL action is supported, attempts to open the default mail client
     * to compose an email to the address specified in the email label. If the application is running in a test
     * environment or if the Desktop or the MAIL action is not supported, uses the provided DesktopInterface mock to
     * simulate the mail action. Else displays an error alert.
     */
    public void handleEmailClicked() {
        String emailAddress = email.getText();
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            try {
                if (!isTestEnvironment && Desktop.isDesktopSupported() && Desktop.getDesktop()
                        .isSupported(Desktop.Action.MAIL)) {
                    // Running in real environment, use Desktop class
                    Desktop.getDesktop().mail(new URI("mailto:" + emailAddress));
                } else if (isTestEnvironment) {
                    // Running in test environment or Desktop not supported, use DesktopInterface
                    mockDesktop.mail(new URI("mailto:" + emailAddress));
                } else {
                    // Neither Desktop nor DesktopInterface available, show error alert
                    showEmailErrorAlert();
                }
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

    /*
     * Displays an error alert when there is a failure to open the email client.
     * The alert prompts the user to ensure they have an email client configured.
     */
    private void showEmailErrorAlert() {
        if (isTestEnvironment) {
            emailAlertShown = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to open email client. Please make sure you have an email client configured.");
            alert.showAndWait();
        }
    }

    public boolean isEmailErrorAlertShown() {
        return emailAlertShown;
    }

    /*
     * Sets desktop stub for testing.
     */
    public void setDesktopWrapper(DesktopInterface desktopWrapper) {
        this.mockDesktop = desktopWrapper;
    }
}
