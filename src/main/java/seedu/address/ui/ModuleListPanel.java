package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.module.ModuleCode;


/**
 * Panel containing the list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleListPanel.fxml";

    @FXML
    private ListView<ModuleCode> moduleListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModuleListPanel(ObservableList<ModuleCode> moduleList) {
        super(FXML);
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ModuleCode} using a {@code ModuleCard}.
     */
    class ModuleListViewCell extends ListCell<ModuleCode> {
        @Override
        protected void updateItem(ModuleCode moduleCode, boolean empty) {
            super.updateItem(moduleCode, empty);

            if (empty || moduleCode == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ModuleCard(moduleCode).getRoot());
            }
        }
    }
}
