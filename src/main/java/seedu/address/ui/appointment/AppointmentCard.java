package seedu.address.ui.appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "appointmentView/AppointmentListCard.fxml";

    public final Appointment appointment;

    public final Person person;

    @FXML
    private HBox appointmentCardPane;
    @FXML
    private Label appointmentDisplayedIndex;
    @FXML
    private Label nameOfAppointmentHolder;
    @FXML
    private Label appointmentTime;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public AppointmentCard(Appointment appointment, Person person, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        this.person = person;

        // Display
        appointmentDisplayedIndex.setText(displayedIndex + ". ");
        nameOfAppointmentHolder.setText(person.getName().fullName);
        appointmentTime.setText(appointment.getAppointmentTimeString());
    }
}
