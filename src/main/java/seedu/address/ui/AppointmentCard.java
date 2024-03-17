package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appt;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label nric;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label timePeriod;
    @FXML
    private Label appointmentType;
    @FXML
    private Label note;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AppointmentCard(Appointment appt, int displayedIndex) {
        super(FXML);
        this.appt = appt;
        id.setText(displayedIndex + ". ");
        name.setText(appt.getName().toString() + " - ");
        date.setText(appt.getDate().value.toString());
        timePeriod.setText(appt.getTimePeriod().getStartTime() + " - " + appt.getTimePeriod().getEndTime());
        appointmentType.setText(appt.getAppointmentType().toString());
        note.setText("Notes: " + appt.getNote().toString());
    }
}
