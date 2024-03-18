package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.startup.Startup;

/**
 * An UI component that displays information of a {@code Startup}.
 */
public class StartupCard extends UiPart<Region> {

    private static final String FXML = "StartupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Startup startup;

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
    private FlowPane industryAndFundingStage;

    @FXML
    private Label note; // Add this field for note
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StartupCode} with the given {@code Startup} and index to display.
     */
    public StartupCard(Startup startup, int displayedIndex) {
        super(FXML);
        this.startup = startup;
        id.setText(displayedIndex + ". ");
        name.setText(startup.getName().fullName);
        phone.setText(startup.getPhone().value);
        address.setText(startup.getAddress().value);
        email.setText(startup.getEmail().value);

        String fundingLevel = startup.getFundingStage().value;
        if (fundingLevel.equals("PS")) {
            fundingLevel = "PRE-SEED";
        } else if (fundingLevel.equals("S")) {
            fundingLevel = "SEED";
        } else {
            fundingLevel = "SERIES " + fundingLevel;
        }
        industryAndFundingStage.getChildren().addAll(
                new Label(startup.getIndustry().value),
                new Label(fundingLevel));
        createNoteSection();
        startup.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void createNoteSection() {
        // Create note label
        note = new Label(startup.getNote().value);
        note.setVisible(true); // Note is always visible
        note.setWrapText(true); // Allow text wrapping if too long

        // Layout note section
        VBox noteSection = new VBox();
        noteSection.setAlignment(Pos.TOP_LEFT); // Align content to the top left
        noteSection.setPadding(new Insets(10)); // Add padding around the note section
        noteSection.setPrefWidth(200); // Set preferred width for the note section
        noteSection.setStyle("-fx-border-color: Transparent;"
                + "-fx-border-width: 1px;"); // Add border around the note section

        // Add note label to the note section
        noteSection.getChildren().addAll(new Label(""), note);

        // Add some padding between note section and startup card box
        VBox.setMargin(noteSection, new Insets(10));

        // Add note section to cardPane
        cardPane.getChildren().add(noteSection);
    }

}
