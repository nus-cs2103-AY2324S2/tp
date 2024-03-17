package seedu.address.ui.appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "appointmentView/AppointmentListCard.fxml";

    public final Appointment appointment;

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
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        appointmentDisplayedIndex.setText(displayedIndex + ". ");
        // TODO: CHANGE TO PERSON NAME; blocked by:
        // https://github.com/AY2324S2-CS2103T-T10-2/tp/issues/58
        nameOfAppointmentHolder.setText(appointment.getPersonIdString());
        appointmentTime.setText(appointment.getAppointmentTimeString());
    }
}
