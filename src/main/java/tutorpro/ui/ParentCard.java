package tutorpro.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tutorpro.model.person.parent.Parent;


/**
 * An UI component that displays information of a {@code Parent}.
 */
public class ParentCard extends UiPart<Region> {

    private static final String FXML = "ParentListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Parent parent;

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
    private FlowPane students;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ParentCard(Parent parent, int displayedIndex) {
        super(FXML);
        this.parent = parent;
        id.setText(displayedIndex + ". ");
        name.setText(parent.getName().fullName);
        phone.setText(parent.getPhone().value);
        address.setText(parent.getAddress().value);
        email.setText(parent.getEmail().value);
        parent.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        parent.getStudents().stream()
            .sorted(Comparator.comparing(student -> student.getName().fullName))
            .forEach(student -> students.getChildren().add(new Label(student.getName().fullName)));
    }
}
