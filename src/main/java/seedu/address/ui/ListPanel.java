package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * Panel containing the list of persons, appointments and case logs.
 */
public class ListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @FXML
    private ListView<Patient> personListView;
    @FXML
    private ListView<Appointment> appointmentListView;
    @FXML
    private ListView<Patient> caseLogListView;
    /**
     * Creates a {@code ListPanel} with the given {@code ObservableLists}.
     */
    public ListPanel(ObservableList<Patient> patientList, ObservableList<Appointment> appointmentList) {
        super(FXML);
        personListView.setItems(patientList);
        appointmentListView.setItems(appointmentList);
        //Temporarily removed for v1.2
        //caseLogListView.setItems(personList);

        personListView.setCellFactory(listView -> new PersonListViewCell());
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
        //Temporarily removed for v1.2
        //caseLogListView.setCellFactory(listView -> new CaseLogListViewCell());
    }
}
