package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Appointment}.
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

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label doctorNric;
    @FXML
    private Label patientNric;
    @FXML
    private Label appointmentDate;
    @FXML
    private Label appointmentId;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appt, int displayedIndex) {
        super(FXML);
        this.appointment = appt;
        id.setText(displayedIndex + ". ");
        doctorNric.setText(appointment.getDoctoNric().nric);
        patientNric.setText(appointment.getPatientNric().nric);
        appointmentDate.setText(appointment.getAppointmentDate().toString());
        appointmentId.setText(appointment.getAppointmentId().appointmentId);
    }
}
