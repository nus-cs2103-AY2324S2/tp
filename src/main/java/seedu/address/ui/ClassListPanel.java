package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.classes.ClassList;

/**
 * Panel containing the list of classes.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";

    @FXML
    private ListView<ClassList> classListView;

    /**
     * Creates a {@code ClassListPanel} with the given {@code ObservableList}.
     */
    public ClassListPanel(ObservableList<ClassList> classList) {
        super(FXML);
        classListView.setItems(classList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ClassList} using a {@code ClassCard}.
     */
    class ClassListViewCell extends ListCell<ClassList> {
        @Override
        protected void updateItem(ClassList classList, boolean empty) {
            super.updateItem(classList, empty);

            if (empty || classList == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassCard(classList).getRoot());
            }
        }
    }
}

