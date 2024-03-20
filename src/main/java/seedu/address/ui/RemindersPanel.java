package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of reminders.
 */
public class RemindersPanel {
    private static final String FXML = "RemindersPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RemindersPanel.class);

    // @FXML
    // private ListView<xxx> remindersListView;

//    /**
//     * Creates a {@code RemindersPanel} with the given {@code ObservableList}.
//     */
//    public RemindersPanel(ObservableList<xxx> remindersList) {
//        super(FXML);
//        remindersListView.setItems(personList);
//        remindersListView.setCellFactory(listView -> new ClientListViewCell());
//    }
//
//    /**
//     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code ClientListCard}.
//     */
//    class ClientListViewCell extends ListCell<Person> {
//        @Override
//        protected void updateItem(Person person, boolean empty) {
//            super.updateItem(person, empty);
//
//            if (empty || person == null) {
//                setGraphic(null);
//                setText(null);
//            } else {
//                setGraphic(new ClientListCard(person, getIndex() + 1).getRoot());
//            }
//        }
//    }
    
}
