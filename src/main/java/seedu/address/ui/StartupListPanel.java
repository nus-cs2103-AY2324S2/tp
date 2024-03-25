package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.startup.Startup;

/**
 * Panel containing the list of startups.
 */
public class StartupListPanel extends UiPart<Region> {
    private static final String FXML = "StartupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StartupListPanel.class);

    @FXML
    private ListView<Startup> startupListView;

    /**
     * Creates a {@code StartupListPanel} with the given {@code ObservableList}.
     */
    public StartupListPanel(ObservableList<Startup> startupList) {
        super(FXML);
        startupListView.setItems(startupList);
        startupListView.setCellFactory(listView -> new StartupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Startup} using a {@code StartupCard}.
     */
    class StartupListViewCell extends ListCell<Startup> {
        @Override
        protected void updateItem(Startup startup, boolean empty) {
            super.updateItem(startup, empty);

            if (empty || startup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StartupCard(startup, getIndex() + 1).getRoot());
            }
        }
    }

}
