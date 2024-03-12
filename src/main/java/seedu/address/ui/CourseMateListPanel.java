package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.coursemate.CourseMate;

/**
 * Panel containing the list of course mates.
 */
public class CourseMateListPanel extends UiPart<Region> {
    private static final String FXML = "CourseMateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseMateListPanel.class);

    @FXML
    private ListView<CourseMate> courseMateListView;

    /**
     * Creates a {@code CourseMateListPanel} with the given {@code ObservableList}.
     */
    public CourseMateListPanel(ObservableList<CourseMate> courseMateList) {
        super(FXML);
        courseMateListView.setItems(courseMateList);
        courseMateListView.setCellFactory(listView -> new CourseMateListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CourseMate} using a {@code CourseMateCard}.
     */
    class CourseMateListViewCell extends ListCell<CourseMate> {
        @Override
        protected void updateItem(CourseMate courseMate, boolean empty) {
            super.updateItem(courseMate, empty);

            if (empty || courseMate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CourseMateCard(courseMate, getIndex() + 1).getRoot());
            }
        }
    }

}
