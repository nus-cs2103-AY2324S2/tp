package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates an {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<Event> eventList, ObservableValue<Event> selectedEvent) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell(selectedEvent));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        private final ObservableValue<Event> selectedEvent;

        public EventListViewCell(ObservableValue<Event> selectedEvent) {
            this.selectedEvent = selectedEvent;

            // Add a listener to the selectedEvent property to update the cell style when it changes
            selectedEvent.addListener((observable, oldValue, newValue) -> updateCellStyle(newValue));
        }

        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
                setStyle("");
                setBorder(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
                updateCellStyle(selectedEvent.getValue());
            }
        }

        private void updateCellStyle(Event selectedEvent) {
            if (selectedEvent != null && getItem() != null && getItem().equals(selectedEvent)) {
                setStyle("-fx-border-color: #4285f4; -fx-border-width: 2px;");
            } else {
                setStyle("");
            }
        }
    }

}
