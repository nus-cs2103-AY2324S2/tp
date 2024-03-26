package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of Meeting Info.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";

    @FXML
    private ListView<Person> meetingListView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList} of {@code Person}.
     */
    public MeetingListPanel(ObservableList<Person> personList) {
        super(FXML);
        meetingListView.setItems(personList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
