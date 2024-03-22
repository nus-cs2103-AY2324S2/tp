package seedu.address.ui;

// import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
// import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class CurrentProjectPanel extends UiPart<Region> {
    private static final String FXML = "CurrentProjectPanel.fxml";
    // private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> currentProjectView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CurrentProjectPanel(ObservableList<Person> currentProject) {
        super(FXML);
        currentProjectView.setItems(currentProject);
        currentProjectView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskListPanel(person).getRoot());
            }
        }
    }

}
