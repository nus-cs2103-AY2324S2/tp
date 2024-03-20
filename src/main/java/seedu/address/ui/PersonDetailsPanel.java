package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays the details of a {@code Person}.
 */
public class PersonDetailsPanel extends UiPart<Region> {
    public static final String FXML = "PersonDetailsPanel.fxml";

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
    private ImageView qrcode;
    @FXML
    private Label note;

    public PersonDetailsPanel() {
        super(FXML);
    }

    /**
     * Set fields with information from the person
     *
     * @param person the Person object containing the information to update the fields with
     */
    public void update(Person person) {
        // Set fields with information from the person
        name.setText(person.getName().getValue());
        phone.setText(person.getPhone().getValue());
        address.setText(person.getAddress().getValue());
        email.setText(person.getEmail().getValue());

        // Clear tags and set new ones
        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.getValue()))
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        note.setText(person.getNote().getValue());
        qrcode.setImage(new Image(person.getQrCodePath().toUri().toString()));

        // Bind manageability (presence) of node based on presence of value for optional
        // fields
        address.setVisible(!person.getAddress().getValue().isEmpty());
        email.setVisible(!person.getEmail().getValue().isEmpty());
        note.setVisible(!person.getNote().getValue().isEmpty());

        address.managedProperty().bind(address.visibleProperty());
        email.managedProperty().bind(email.visibleProperty());
        note.managedProperty().bind(note.visibleProperty());
    }
}
