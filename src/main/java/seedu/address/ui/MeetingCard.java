package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.Meeting;

/**
 * An UI component that displays information of a {@code Person}'s meeting.
 * This class represents a card on the UI that contains details of a meeting
 * associated with a specific person.
 */
public class MeetingCard extends UiPart<Region> {
    private static final String FXML = "MeetingListCard.fxml";

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label meetingText;

    /**
     * Creates a {@code MeetingCard} with the given {@code Person} and index to display.
     * This constructor initializes the UI card with the person's meeting information.
     *
     * @param person The person whose meeting information is to be displayed.
     * @param displayedIndex The index number of the meeting in the list.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        name.setText(meeting.getName());
        meetingText.setText(meeting.toString()); // Assuming Meeting has a getText() method
    }
}
