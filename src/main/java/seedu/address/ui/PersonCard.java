package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Category;
import seedu.address.model.person.Participant;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     * issue on AddressBook level 4</a>
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
    private Label category;
    @FXML
    private Label group;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + "");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        group.setText(getGroupNumber(person));
        category.setText(person.getCategory().value);
        category.getStyleClass().add(getCategoryStyleClass(person.getCategory()));
    }

    private String getCategoryStyleClass(Category category) {
        return "category-label-" + category.value.toLowerCase();
    }

    private String getGroupNumber(Person person) {
        switch (person.getCategory().value) {
        case "PARTICIPANT":
            return Integer.toString(((Participant) person).getGroupNumber());
        case "STAFF":
            return Integer.toString(((Staff) person).getGroupNumber());
        default:
            return "N/A (Sponsors)";
        }
    }
}
