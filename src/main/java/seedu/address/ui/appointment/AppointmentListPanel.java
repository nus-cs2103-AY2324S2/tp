package seedu.address.ui.appointment;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of appointments.
 * Each appointment is also displayed along with some fields of its associated
 * person.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "appointmentView/AppointmentListPanel.fxml";

    @FXML
    private ListView<Appointment> appointmentListView;

    Logic logic;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     * Logic is injected because every update to {@code ObservableList<Appointment>}
     * requires a corresponding call to get the updated list's appointments' associated
     * Persons. This is because {@code ObservableList<Appointment>} does not have any
     * relationship with {@code Person}.
     */
    public AppointmentListPanel(Logic logic, ObservableList<Appointment> appointmentList) {
        super(FXML);
        this.logic = logic;
        appointmentListView.setItems(appointmentList);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment}
     * using a {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                Person person = logic.getPersonById(appointment.getPersonId());
                setGraphic(new AppointmentCard(appointment, person, getIndex() + 1).getRoot());
            }
        }
    }

}
