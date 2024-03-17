package staffconnect.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import staffconnect.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meetings} withing a person.
 */
public class MeetingsCard extends UiPart<Region> {

    private static final String FXML = "MeetingsListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label description;
    @FXML
    private Label date;

    /**
     * Creates a {@code MeetingCode} with the given {@code Meeting} and index to display.
     */
    public MeetingsCard(Meeting meeting) {
        super(FXML);
        System.out.println("SEeting the description here");
        description.setText(meeting.getDescription().toString());
        date.setText(meeting.getStartDate().toString());
    }

}
