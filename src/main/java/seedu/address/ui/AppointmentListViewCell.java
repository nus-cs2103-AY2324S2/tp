package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.appointment.Appointment;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code PersonCard}.
 */
public class AppointmentListViewCell extends ListCell<Appointment> {
    @Override
    protected void updateItem(Appointment appointment, boolean empty) {
        super.updateItem(appointment, empty);

        if (empty || appointment == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
        }
    }
}
