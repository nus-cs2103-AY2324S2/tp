package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
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
    private Label result;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        String text = "";
        EntryList e = person.getList();
        for (int i = 0; i < e.size(); i++) {
            Entry entry = e.get(i);
            if (!entry.getCategory().equals("Name")) {
                text = text + e.get(i).toString() + "\n";
            }
        }
        id.setText(displayedIndex + ". ");
        id.setFont(Font.loadFont("./PressStart2P-Regular.ttf", 120));
        name.setText(person.getEntry("Name").toString());
        name.setFont(Font.loadFont("./PressStart2P-Regular.ttf", 120));
        result.setText(text);
        result.setFont(Font.loadFont("./PressStart2P-Regular.ttf", 120));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

