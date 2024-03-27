package vitalconnect.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import vitalconnect.commons.core.LogsCenter;
import vitalconnect.model.Appointment;

/**
 * A UI component that displays a list of {@code Appointment} objects.
 * <p>
 * This component is responsible for rendering the appointments in the UI,
 * with each appointment represented as an individual card within a list view.
 * It uses {@code AppointmentCard} to visually represent each appointment's data.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<Appointment> appointmentListView;

    /**
     * Creates an {@code AppointmentListPanel} with the given list of appointments.
     *
     * @param appointmentList An ObservableList of appointments to be displayed in the panel.
     */
    public AppointmentListPanel(ObservableList<Appointment> appointmentList) {
        super(FXML);
        appointmentListView.setItems(appointmentList);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Appointment} using an {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
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
}


