package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.module.ClassList;

/**
 * Panel containing the list of classes.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";

    @FXML
    private ListView<ClassList> classListView;
    private ObservableList<ClassList> classList;

    /**
     * Creates a {@code ClassListPanel} with the given {@code ObservableList} of {@code ClassList}.
     */
    public ClassListPanel(ObservableList<ClassList> classList) {
        super(FXML);
        this.classList = classList;
        classListView.setItems(classList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    /**
     * Updates the list of classes displayed in the panel.
     */
    public void updateClassList(ObservableList<ClassList> updatedClassList) {
        classList = updatedClassList;
        classListView.setItems(classList);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ClassList} using a {@code ClassListCard}.
     */
    class ClassListViewCell extends ListCell<ClassList> {
        @Override
        protected void updateItem(ClassList classList, boolean empty) {
            super.updateItem(classList, empty);

            if (empty || classList == null) {
                setGraphic(null);
                setText(null);
            } else {
                ClassListCard classListCard = new ClassListCard();
                classListCard.updateClassList(classList);
                setGraphic(classListCard.getRoot());
            }
        }
    }
}


