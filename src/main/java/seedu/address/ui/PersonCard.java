package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
    private Label email;
    @FXML
    private Label studentid;
    @FXML
    private FlowPane attendance;
    @FXML
    private Label description;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        studentid.setText(person.getStudentId().value);
        email.setText(person.getEmail().value);
        person.getAttendances().stream()
                .sorted(Comparator.comparing(attendance -> LocalDate.parse(attendance.attendanceName.getDate(),
                        DATE_TIME_FORMATTER)))
                .forEach(attendance -> {
                    FontAwesomeIconView icon = createIconBasedOnStatus(attendance.attendanceName.getStatus());
                    // Creating an HBox to hold the label and the icon
                    HBox hbox = new HBox();
                    hbox.setSpacing(5); // Set spacing between elements in HBox

                    // Creating a label for the date
                    Label dateLabel = new Label(attendance.attendanceName.getDate() + ": ");
                    hbox.getChildren().add(dateLabel); // Add date label to HBox

                    // Add the icon to the HBox, positioning it to the right of the label
                    hbox.getChildren().add(icon);

                    // Wrap the HBox in another HBox or a StackPane for the border
                    HBox container = new HBox(hbox); // Using HBox as a container
                    container.setPadding(new Insets(5)); // Padding inside the container
                    container.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    container.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY,
                            Insets.EMPTY)));

                    // Add the container to the attendance FlowPane
                    this.attendance.getChildren().add(container);
                });
      description.setText(person.getDescription().value);
    }

    /**
     * Create Icon based on the status of the attendance record.
     *
     * @param status of the attendance icon
     * @return cross icon if the status is 0, tick icon if the status is 1, circle if the status is 2
     */
    private FontAwesomeIconView createIconBasedOnStatus(String status) {
        FontAwesomeIconView iconView;
        switch (status) {
        case "0":
            iconView = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
            iconView.setFill(Color.RED);
            break;

        case "1":
            iconView = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
            iconView.setFill(Color.GREEN);
            break;

        case "2":
            iconView = new FontAwesomeIconView(FontAwesomeIcon.CIRCLE);
            iconView.setFill(Color.BLUE);
            break;

        default:
            iconView = new FontAwesomeIconView(FontAwesomeIcon.QUESTION_CIRCLE);
            iconView.setFill(Color.GRAY);
        }

        iconView.setSize("16");
        return iconView;

    }
}
