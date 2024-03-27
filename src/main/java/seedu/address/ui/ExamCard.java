package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.exam.Exam;

/**
 * An UI component that displays information of a {@code Exam}.
 */
public class ExamCard extends UiPart<Region> {

    private static final String FXML = "ExamListCard.fxml";

    public final Exam exam;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label maxScore;

    /**
     * Creates a {@code ExamCard} with the given {@code Exam} and index to display.
     */
    public ExamCard(Exam exam, int displayedIndex) {
        super(FXML);
        this.exam = exam;
        id.setText(displayedIndex + ". ");
        name.setText(exam.getName());
        maxScore.setText(String.valueOf(exam.getMaxScore()));
    }
}
