package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.coursemate.CourseMate;

/**
 * An UI component that displays information of a {@code CourseMate}.
 */
public class CourseMateCard extends UiPart<Region> {

    private static final String FXML = "CourseMateListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ContactList level 4</a>
     */

    public final CourseMate courseMate;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox cardBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label rating;
    @FXML
    private FlowPane skills;

    /**
     * Creates a {@code CourseMateCode} with the given {@code CourseMate} and index to display.
     */
    public CourseMateCard(CourseMate courseMate, int displayedIndex) {
        super(FXML);
        this.courseMate = courseMate;
        id.setText("#" + displayedIndex + " ");
        name.setText(courseMate.getName().fullName);
        phone.setText(courseMate.getPhone().value);
        email.setText(courseMate.getEmail().value);


        if (courseMate.getTelegramHandle() == null) {
            cardBox.getChildren().remove(telegramHandle);
        } else {
            telegramHandle.setText(courseMate.getTelegramHandle().value);
        }
        if (courseMate.getRating().ratingText.isEmpty()) {
            cardBox.getChildren().remove(rating);
        } else {
            rating.setText(courseMate.getRating().ratingText);
        }
        courseMate.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));
    }
}
