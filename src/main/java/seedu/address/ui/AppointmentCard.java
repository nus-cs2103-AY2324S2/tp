package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * A UI component that displays information of an {@code Appointment}.
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
    private Label name;
    @FXML
    private Label appointmentId;
    @FXML
    private Label appointmentDateTime;
    @FXML
    private Label hasAttended;
    @FXML
    private Label appointmentDescription;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} to display.
     */
    public AppointmentCard(Appointment appointment) {
        super(FXML);
        this.appointment = appointment;

        appointmentId.setText(appointment.getAppointmentId() + ". ");
        //TODO: replace student id with student name
        name.setText("StudentId: " + Integer.toString(appointment.getStudentId()));
        appointmentDateTime.setText(appointment.getAppointmentDateTime().toString().replace("T", " "));
        hasAttended.setText(Boolean.toString(appointment.getAttendedStatus()));
        appointmentDescription.setText(appointment.getAppointmentDescription());
    }
}
