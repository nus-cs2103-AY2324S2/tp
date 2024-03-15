package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
     * Creates a {@code CourseMateListPanel} with the given {@code ObservableList}
     * and {@code CourseMateDetailPanel}.
     */
    public CourseMateListPanel(ObservableList<CourseMate> courseMateList,
                               MainWindow mainWindow) {
        super(FXML);
        courseMateListView.setItems(courseMateList);
        courseMateListView.setCellFactory(listView -> new CourseMateListViewCell());
        courseMateListView.setOnMouseClicked(
                new CourseMateListClickHandler(courseMateListView, mainWindow));
        courseMateListView.setOnKeyPressed(
                new CourseMateListPressHandler(courseMateListView, mainWindow));
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

class CourseMateListClickHandler implements EventHandler<MouseEvent> {
    private ListView<CourseMate> courseMateListView;
    private MainWindow mainWindow;

    public CourseMateListClickHandler(ListView<CourseMate> courseMateListView,
                                      MainWindow mainWindow) {
        this.courseMateListView = courseMateListView;
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            CourseMate selectedCourseMate =
                    courseMateListView.getSelectionModel().getSelectedItem();
            mainWindow.handleCourseMateListSelect(selectedCourseMate);
        }
    }
}

class CourseMateListPressHandler implements EventHandler<KeyEvent> {
    private ListView<CourseMate> courseMateListView;
    private MainWindow mainWindow;

    public CourseMateListPressHandler(ListView<CourseMate> courseMateListView,
                                      MainWindow mainWindow) {
        this.courseMateListView = courseMateListView;
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            CourseMate selectedCourseMate =
                    courseMateListView.getSelectionModel().getSelectedItem();
            mainWindow.handleCourseMateListSelect(selectedCourseMate);
        }
    }
}
