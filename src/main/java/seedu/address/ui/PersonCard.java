package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
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
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane subjectWithGrade;
    @FXML
    private Label attendance;
    @FXML
    private Label payment;
    @FXML
    private FlowPane dateTimes;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        subjectWithGrade.getChildren().add(new Label(person.getSubject().value));
        subjectWithGrade.getChildren().add(new Label(person.getGrade().value));
        attendance.setText(person.getAttendance().value);
        payment.setText(person.getPayment().value);
        dateTimes.setHgap(5);
        person.getDateTimes().stream()
                .sorted(Comparator.comparing(dateTime -> dateTime.value))
                .forEach(dateTime -> dateTimes.getChildren()
                        .add(new Label(LocalDateTime.parse(dateTime.value,
                                        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"))
                                .format(DateTimeFormatter.ofPattern("MMM d uuuu h:mma")))));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
