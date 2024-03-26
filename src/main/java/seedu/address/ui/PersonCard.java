package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    private VBox vBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, boolean isFullView) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().value);
        phone.setText(person.getPhone().value);
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (isFullView) {
            Label sex = new Label(getFullSexString(person.getSex().value));
            Label employmentType = new Label(getFullEmploymentTypeString(person.getEmploymentType().value));
            Label address = new Label(person.getAddress().value);
            Label bankDetails = new Label(person.getBankDetails().value);
            Label hoursWorked = new Label(person.getWorkHours().toString());
            vBox.getChildren().addAll(new Label[]{sex, employmentType, address, bankDetails, hoursWorked});
        }
    }

    /**
     * Returns the full employment type string based on the short form string returned from EmploymentType.
     */
    private String getFullEmploymentTypeString(String shortFormString) {
        if (shortFormString.equals("ft")) {
            return "Full-Time";
        } else if (shortFormString.equals("pt")) {
            return "Part-Time";
        }
        return "Invalid Employment Type"; // should not reach here
    }

    /**
     * Returns the full sex string based on the short form string returned from Sex.
     */
    private String getFullSexString(String shortFormString) {
        if (shortFormString.equals("m")) {
            return "Male";
        } else if (shortFormString.equals("f")) {
            return "Female";
        }
        return "Invalid Sex"; // should not reach here
    }
}
