package staffconnect.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import staffconnect.model.meeting.Meeting;

/**
 * A UI component that displays information of a {@code Meetings} withing a person.
 */
public class MeetingsCard extends UiPart<Region> {

    private static final String FXML = "MeetingsListCard.fxml";
    @FXML
    private Label description;
    @FXML
    private Label date;

    /**
     * Creates a {@code MeetingCode} with the given {@code Meeting} and index to display.
     */
    public MeetingsCard(Meeting meeting) {
        super(FXML);
        description.setText(meeting.getDescription().toString());
        date.setText(meeting.getStartDate().toString());
    }

}
