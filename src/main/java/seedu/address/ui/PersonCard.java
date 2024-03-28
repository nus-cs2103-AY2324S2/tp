package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    private FlowPane tags;
    @FXML
    private Label company;
    @FXML
    private Label meeting;
    @FXML
    private Circle priorityDot;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        if (person.isStarred()) {
            name.setText(person.getName().fullName + " ★");
        } else {
            name.setText(person.getName().fullName);
        }

        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        setCompany();
        setMeeting();

        String priorityValue = person.getPriority().value;
        if ("high".equals(priorityValue)) {
            priorityDot.setFill(Color.RED);
        } else if ("med".equals(priorityValue)) {
            priorityDot.setFill(Color.ORANGE);
        } else {
            priorityDot.setVisible(false);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setCompany() {
        if (!person.getCompany().value.equals("")) {
            company.setText(person.getCompany().value);
            company.setVisible(true);
        } else {
            company.setPrefHeight(0.0);
        }
    }

    private void setMeeting() {
        if (person.getMeeting() != null) {
            meeting.setText(person.getMeeting().toString());
            meeting.setVisible(true);
        } else {
            meeting.setPrefHeight(0.0);
        }
    }

}
