package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;

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
    private VBox classes;
    @FXML
    private Label matric;
    @FXML
    private VBox examScore;

    @FXML
    private ImageView phoneicon;
    @FXML
    private ImageView emailicon;
    @FXML
    private ImageView addressicon;

    private Image phoneIcon = new Image(this.getClass().getResourceAsStream("/images/phoneicon.png"));
    private Image emailIcon = new Image(this.getClass().getResourceAsStream("/images/emailicon.png"));
    private Image addressIcon = new Image(this.getClass().getResourceAsStream("/images/addressicon.png"));

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, ObservableValue<Exam> selectedExam) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        matric.setText(person.getMatric().toString());
        if (matric.getText().isEmpty()) {
            matric.setVisible(false);
            matric.setManaged(false);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (!person.getReflection().toString().isEmpty()) {
            classes.getChildren().add(new Label(person.getReflection().toString()));
        }
        if (!person.getStudio().toString().isEmpty()) {
            classes.getChildren().add(new Label(person.getStudio().toString()));
        }

        // Update exam score whenever updated
        Exam selectedExamValue = selectedExam.getValue();
        if (selectedExamValue != null) {
            Score score = person.getScores().get(selectedExamValue);
            if (score != null) {
                examScore.getChildren().add(new Label("Score: \n" + String.valueOf(score.getScore())));
            }
        }


        // Add a listener to the selectedExam observable to swtich scores whenever the selected exam changes
        selectedExam.addListener((observable, oldValue, newValue) -> {
            examScore.getChildren().clear(); // Clear the old score
            if (newValue != null) {
                Score score = person.getScores().get(newValue);
                if (score != null) {
                    examScore.getChildren().add(new Label("Score: \n" + String.valueOf(score.getScore())));
                }
            }
        });
        phoneicon.setImage(phoneIcon);
        emailicon.setImage(emailIcon);
        addressicon.setImage(addressIcon);
    }
}
