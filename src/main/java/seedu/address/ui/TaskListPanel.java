package seedu.address.ui;

// import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
// import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    // private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final Person currentProject;

    @FXML
    private Label showingProjectName;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(Person currentProject) {
        super(FXML);
        this.currentProject = currentProject;
        showingProjectName.setText(currentProject.getName().fullName);
    }

}
