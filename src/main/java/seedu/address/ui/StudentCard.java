package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane grades;
    @FXML
    private FlowPane timeslots;

    /**
     * Creates a {@code studentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getTimeslots().stream()
                .sorted(Comparator.comparing(timeslot -> timeslot.timeslot))
                .forEach(timeslot -> timeslots.getChildren().add(new Label(timeslot.timeslot)));
        student.getGrades().stream()
                .sorted(Comparator.comparing(grade -> grade.testName))
                .forEach(grade -> grades.getChildren().add(new Label(
                    String.format("%s: %s%%", grade.testName, grade.grade))));
    }
}
