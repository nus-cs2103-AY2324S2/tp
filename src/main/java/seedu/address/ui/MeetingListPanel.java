package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.Meeting;

/**
 * Panel containing the list of Meeting Info.
 */
public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";

    @FXML
    private ListView<Meeting> meetingListView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList} of {@code Person}.
     */
    public MeetingListPanel(ObservableList<Meeting> meetingList) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }
}
