package seedu.address.ui;

import java.io.IOException;

// import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
// import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.ui.TaskListPanel.TaskListCell;

/**
 * Panel containing the list of persons.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    // private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final Person currentProject;

    @FXML
    private Label showingProjectName;
    @FXML
    private ListView<Task> undoneTaskListView;
    @FXML
    private ListView<Task> doneTaskListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(Person currentProject) {
        super(FXML);
        this.currentProject = currentProject;
        showingProjectName.setText("Showing Project: " + currentProject.getName().fullName);
        // Manually set the items of the ListView

        undoneTaskListView.getItems().clear();
        undoneTaskListView.getItems().addAll(currentProject.getUndoneTasks());

        undoneTaskListView.setCellFactory(param -> new TaskListCell());

        doneTaskListView.getItems().clear();
        doneTaskListView.getItems().addAll(currentProject.getDoneTasks());

        doneTaskListView.setCellFactory(param -> new TaskListCell());
    }

    class TaskListCell extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (empty || task == null) {
            setGraphic(null);
        } else {
            setGraphic(new TaskCard(task).getRoot());
        }
    }
}

}
