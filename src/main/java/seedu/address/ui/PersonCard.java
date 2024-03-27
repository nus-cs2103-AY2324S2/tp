package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.house.Block;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
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
    private Label postalCode;
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
            if (seller.getHouses() != null) {
                seller.getHouses().forEach(house -> {
                    // Create a VBox to hold the house details with spacing between elements
                    VBox houseDetails = new VBox(4);
                    houseDetails.getChildren().add(new Label("Postal Code: " + house.getPostalCode().value));
                    houseDetails.getChildren().add(new Label("Street: " + house.getStreet().value));
                    houseDetails.getChildren().add(new Label("Unit Number: " + house.getUnitNumber().value));

                    // If the house is NonLanded, check for block and level
                    if (house instanceof NonLanded) {
                        Block block = ((NonLanded) house).getBlock();
                        Level level = ((NonLanded) house).getLevel();
                        houseDetails.getChildren().add(new Label("Block: " + (block != null ? block.value : "N/A")));
                        houseDetails.getChildren().add(new Label("Level: " + (level != null ? level.value : "N/A")));
                    }

                    // Add the house details to the houses container
                    housesContainer.getChildren().add(houseDetails);

                    // Add spacing after each house's VBox, but not after the last one
                    if (seller.getHouses().indexOf(house) < seller.getHouses().size() - 1) {
                        housesContainer.getChildren().add(new Region() {
                            {
                                setPrefHeight(20);
                            }
                        });
                    }
                });
            }
        } else {
            // We assumed that buyer does not have a house for now
            housesContainer.setVisible(false);
        }
    }
}
