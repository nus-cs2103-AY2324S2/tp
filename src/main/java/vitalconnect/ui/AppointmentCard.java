package vitalconnect.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import vitalconnect.model.Appointment;

/**
 * A UI component that displays information of an {@code Appointment}.
 * <p>
 * This component is used to display the details of an appointment in the UI, specifically
 * in a list view where each appointment is represented as a card. Each card shows the
 * patient's name and the appointment time, formatted according to the specified pattern.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label time;

    /**
     * Creates an {@code AppointmentCard} with the given {@code Appointment} and index to display.
     *
     * @param appointment The appointment object containing the data to be displayed on the card.
     * @param displayedIndex The index of the appointment in the list, which is used for displaying
     *                       the appointment number on the card.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        name.setText(appointment.getPatientName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        time.setText(appointment.getDateTime().format(formatter));
    }
}


