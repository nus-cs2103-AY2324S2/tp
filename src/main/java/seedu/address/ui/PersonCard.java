package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

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
    private Label salary;
    @FXML
    private Label employment;
    @FXML
    private Label product;
    @FXML
    private Label price;
    @FXML
    private Label skill;
    @FXML
    private Label commission;
    @FXML
    private Label className;
    @FXML
    private FlowPane tags;
    @FXML
    private Label note;
    @FXML
    private Label rating;
    @FXML
    private Label pin;

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
        String currentRating = person.getRating().toString();
        if ("0".equals(currentRating)) {
            rating.setText("");
            rating.setManaged(false);
        } else {
            int intValue = Integer.parseInt(currentRating);
            rating.setText("⭐".repeat(Math.max(0, intValue)));
            rating.setManaged(true);
        }
        if (person.getPin().getIsPinned()) {
            pin.setText("📌    ");
            pin.setManaged(true);
        }
        if (!"No note here".equals(person.getNote().toString())) {
            note.setText(person.getNote().toString());
            note.setManaged(true);
        }
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (person instanceof Staff) {
            Staff staff = (Staff) person;
            salary.setText(staff.getSalary().value);
            employment.setText(staff.getEmployment().value);
            salary.setManaged(true);
            employment.setManaged(true);
            salary.setVisible(true);
            employment.setVisible(true);
        } else if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            product.setText(supplier.getProduct().value);
            price.setText(supplier.getPrice().value);
            product.setManaged(true);
            price.setManaged(true);
            product.setVisible(true);
            price.setVisible(true);
        } else if (person instanceof Maintainer) {
            Maintainer maintainer = (Maintainer) person;
            skill.setText(maintainer.getSkill().value);
            commission.setText(maintainer.getCommission().value);
            skill.setManaged(true);
            commission.setManaged(true);
            skill.setVisible(true);
            commission.setVisible(true);
        }
    }

    public Label getSalary() {
        return salary;
    }

    public Label getEmployment() {
        return employment;
    }
}
