package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Interview interview;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label applicantNamePhone;
    @FXML
    private Label interviewerNamePhone;
    @FXML
    private Label date;
    @FXML
    private Label startEndTime;

    /**
     * Creates a {@code InterviewCard} with the given {@code Interview} and {@code int}.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        id.setText(displayedIndex + ". ");
        applicantNamePhone.setText("Applicant:   " + interview.getApplicant().getName().toString());
        interviewerNamePhone.setText("Interviewer: " + interview.getInterviewer().getName().toString());
        date.setText(interview.getDate().toString());
        startEndTime.setText(interview.getStartTime().toString() + " ~ " + interview.getEndTime().toString());
        description.setText(interview.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewCard)) {
            return false;
        }

        // state check
        InterviewCard card = (InterviewCard) other;
        return id.getText().equals(card.id.getText())
                && interview.equals(card.interview);
    }
}
