package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;
    @FXML
    private ListView<Person> appointmentListView;
    @FXML
    private ListView<Person> caseLogListView;
    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        appointmentListView.setItems(personList);
        caseLogListView.setItems(personList);

        personListView.setCellFactory(listView -> new PersonListViewCell());
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
        caseLogListView.setCellFactory(listView -> new CaseLogListViewCell());
    }
}
