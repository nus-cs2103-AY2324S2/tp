package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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
    private Label housingType;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox housesContainer;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        housingType.setText(person.getHousingType());
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // Check if person is a Seller and display houses (For now, we assume only have seller have house)
        if (person instanceof Seller) {
            Seller seller = (Seller) person;
            seller.getHouses().forEach(house -> {
                Label houseLabel = new Label(house.toString());
                housesContainer.getChildren().add(houseLabel);
            });
        } else {
            // For now, we assumed that buyer does not have a house
            housesContainer.setVisible(false);
        }
    }
}
