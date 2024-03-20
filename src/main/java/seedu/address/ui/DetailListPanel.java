package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel that contains a list of details.
 *
 * Code is adapted from PersonListPanel.java file due to similar functionality.
 */
public class DetailListPanel extends UiPart<Region> {
    private static final String FXML = "DetailListPanel.fxml";

    @FXML
    private ListView<DetailCard> detailListView;

    /**
     * Creates a {@code DetailListPanel} with the given {@code ObservableList}
     */
    public DetailListPanel() {
        super(FXML);
    }
}
