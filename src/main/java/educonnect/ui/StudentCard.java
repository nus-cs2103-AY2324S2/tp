package educonnect.ui;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import educonnect.model.student.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.event.ActionEvent;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label studentID;
    @FXML
    private Label email;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane tags;
    @FXML
    private Hyperlink hyperlink;

    @FXML
    void openLink(ActionEvent action) throws URISyntaxException, IOException {
        String url = student.getLink().toString();
        if (url != null || !url.isEmpty()) {

            Desktop.getDesktop().browse(new URI(url));

        } else {
            System.out.println("No Link has been added yet");
        }
    }
    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        name.setText(student.getName().fullName);
        studentID.setText(student.getStudentId().value);
        email.setText(student.getEmail().value);
        telegram.setText(student.getTelegramHandle().value);

        // Set the visibility of the hyperlink based on whether the student has a non-empty URL
//        String url = student.getLink().url; // Assuming getLink().url returns a String
//        boolean isUrlPresent = url != null && !url.isEmpty();
//        hyperlink.setVisible(isUrlPresent);

        // Optionally, disable the hyperlink if the URL is not present to prevent any action
//        hyperlink.setDisable(!isUrlPresent);

        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
