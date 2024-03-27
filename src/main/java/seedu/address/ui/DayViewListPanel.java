package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.AppointmentView;

/**
 * Panel containing the list of persons.
 */
public class DayViewListPanel extends UiPart<Region> {
    private static final String FXML = "DayViewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DayViewListPanel.class);

    @FXML
    private ListView<AppointmentView> dayViewListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public DayViewListPanel(ObservableList<AppointmentView> dayViewList) {
        super(FXML);
        dayViewListView.setItems(dayViewList);
        dayViewListView.setCellFactory(listView -> new DayViewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code AppointmentCard}.
     */
    class DayViewListViewCell extends ListCell<AppointmentView> {
        @Override
        protected void updateItem(AppointmentView apptView, boolean empty) {
            super.updateItem(apptView, empty);

            if (empty || apptView == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(apptView, getIndex() + 1).getRoot());
            }
        }
    }

}
