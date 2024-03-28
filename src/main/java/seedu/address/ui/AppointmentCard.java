package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;

/**
 * A UI component that displays information of a {@code Appointment}.
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
        timePeriod.setText(appt.getStartTime() + " - " + appt.getEndTime());
        appointmentType.setText(appt.getAppointmentType().toString());
        note.setText("Notes: " + appt.getNote().toString());
        Timeline timeline = getTimeline();
        timeline.play();
        if (appt.getMark().isMarked) {
            cardPane.setStyle("-fx-background-color:" + Colors.MARKED_APPOINTMENT_GREEN_COLOR);
        }
    }

    private Timeline getTimeline() {
        EventHandler<ActionEvent> appointmentMissedEventHandler = event -> {
            if (!appt.getMark().isMarked) {
                bindCardPaneStyle();
            }
        };
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), appointmentMissedEventHandler);
        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        return timeline;
    }

    private void bindCardPaneStyle() {
        cardPane.styleProperty().bind(
                Bindings.when(isPastAppointment(appt))
                        .then("-fx-background-color:" + Colors.PAST_APPOINTMENT_RED_COLOR)
                        .otherwise("")
        );
    }

    private BooleanBinding isPastAppointment(Appointment appt) {
        return Bindings.createBooleanBinding(() -> (
                appt.getStartTime().value.isBefore(LocalTime.now())
                        && !appt.getDate().value.isAfter(LocalDate.now()))
                || appt.getDate().value.isBefore(LocalDate.now())
        );
    }
}
