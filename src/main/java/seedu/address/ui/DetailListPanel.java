package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

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
