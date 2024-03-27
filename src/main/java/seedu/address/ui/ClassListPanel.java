package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Classes;

/**
 * Panel containing the list of classes.
 */
public class ClassListPanel extends UiPart<Region> {

    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @javafx.fxml.FXML
    private ListView<Classes> classListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ClassListPanel(ObservableList<Classes> classList) {
        super(FXML);
        classListView.setItems(classList);
        classListView.setCellFactory(listView -> new ClassListPanel.ClassListViewCell());

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ClassListViewCell extends ListCell<Classes> {
        @Override
        protected void updateItem(Classes classes, boolean empty) {
            super.updateItem(classes, empty);

            if (empty || classes == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassCard(classes, getIndex() + 1).getRoot());
            }
        }
    }
}
