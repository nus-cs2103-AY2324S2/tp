package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label note;
    @FXML
    private Label age;
    @FXML
    private Label sex;
    @FXML
    private Label ic;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        String fullNote = person.getNote().value;
        int maxLineLength = 30; // Maximum length of each line before truncation
        String[] lines = fullNote.split("\\r?\\n"); // Split the note into lines

        StringBuilder truncatedNoteBuilder = new StringBuilder();
        if (lines.length > 0) {
            String firstLine = lines[0];
            if (firstLine.length() > maxLineLength) {
                // Truncate the first line and add an ellipsis
                truncatedNoteBuilder.append("Note: ").append(firstLine.substring(0, maxLineLength)).append("...\n");
            } else {
                truncatedNoteBuilder.append("Note: ").append(firstLine).append("\n");
            }

            // Append the rest of the lines with the same indentation
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (line.length() > maxLineLength) {
                    // Truncate the line and add an ellipsis
                    truncatedNoteBuilder.append("            ")
                            .append(line.substring(0, maxLineLength)).append("...\n");
                } else {
                    truncatedNoteBuilder.append("            ").append(line).append("\n");
                }
            }
        }

        String truncatedNote = truncatedNoteBuilder.toString().trim(); // Remove trailing newline
        note.setText(truncatedNote);

        ic.setText(person.getIdentityCardNumber().value);
        age.setText(String.valueOf(person.getAge().value));
        sex.setText(person.getSex().value);
        address.setText(person.getAddress().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
