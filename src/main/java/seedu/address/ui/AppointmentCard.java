package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;

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

    public final Appointment appt;
    public final AppointmentView apptView;

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
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(AppointmentView apptView, int displayedIndex) {
        super(FXML);
        this.apptView = apptView;
        this.appt = apptView.getAppointment();
        name.setText(apptView.getName().fullName);
        id.setText(displayedIndex + ". ");
        nric.setText(appt.getNric().toString() + " - ");
        date.setText(appt.getDate().value.toString());
        timePeriod.setText(appt.getTimePeriod().getStartTime() + " - " + appt.getTimePeriod().getEndTime());
        appointmentType.setText(appt.getAppointmentType().toString());
        note.setText("Notes: " + appt.getNote().toString());

        //make appointment green depending on mark status
        cardPane.styleProperty().bind(
            javafx.beans.binding.Bindings.when(
                javafx.beans.binding.Bindings.createBooleanBinding(() -> (
                    appt.getMark().mark)
                )
            )
            .then("-fx-background-color: #33B864")
            .otherwise("-fx-background-color: defaultColor")
        );
    }
}
