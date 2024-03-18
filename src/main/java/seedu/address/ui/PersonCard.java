package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final Map<Integer, String> tagMap = new HashMap<>();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private Label remark;
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
    private FlowPane tags;
    @FXML
    private FlowPane attendances;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + "");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.getStyleClass().add(getStyleClassForTag(tag.tagName));
                    tags.getChildren().add(label);
                });
        person.getAttendances().stream()
                .sorted(Comparator.comparing(attendance -> attendance.attendanceDate))
                .forEach(attendance -> {
                    String formattedDate = attendance.attendanceDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    attendances.getChildren().add(new Label(formattedDate));
                });
    }

    // set a custom tag list to follow style class
    static {
        tagMap.put(0, "tag1");
        tagMap.put(1, "tag2");
        tagMap.put(2, "tag3");
        tagMap.put(3, "tag4");
        tagMap.put(4, "tag5");
    }

    /**
     * Retrieves and returns the tag name for custom tag colour.
     *
     * @param tagName The name of the tag assigned to the Person.
     * @return String representation of the hashed tag.
     */
    private String getStyleClassForTag(String tagName) {
        int hash = Math.abs(tagName.hashCode()) % 5;
        return tagMap.get(hash);
    }
}
