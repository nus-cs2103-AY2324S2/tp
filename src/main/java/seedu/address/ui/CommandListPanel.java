package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.command.CommandInfo;

/**
 * Represents a panel that displays a list of commands and their descriptions in the UI.
 */
public class CommandListPanel extends UiPart<Region> {
    private static final String FXML = "CommandListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommandListPanel.class);

    @FXML
    private ListView<CommandInfo> commandListView;

    /**
     * Creates a {@code CommandListPanel} with the given {@code ObservableList}.
     */
    public CommandListPanel() {
        super(FXML);
        loadCommands();
    }

    /**
     * Populates the command list view with a set of predefined commands.
     */
    private void loadCommands() {
        ObservableList<CommandInfo> commandList = FXCollections.observableArrayList(
                new CommandInfo("list", "Lists all contact"),
                new CommandInfo("add", "Adds a new contact"),
                new CommandInfo("delete", "Deletes a contact"),
                new CommandInfo("edit", "Edits a contact"),
                new CommandInfo("clear", "Clears all records"),
                new CommandInfo("find", "Find contacts using keyword"),
                new CommandInfo("help", "Shows help"),
                new CommandInfo("book", "Books a consultation"),
                new CommandInfo("view", "Views all consultations"),
                new CommandInfo("update", "Updates a consultation"),
                new CommandInfo("cancel", "Cancels a consultation"),
                new CommandInfo("search", "Search a consultation"),
                new CommandInfo("theme", "Changes the current theme")
        );

        commandListView.setItems(commandList);
        commandListView.setCellFactory(listView -> new CommandListViewCell());
    }

    /**
     * Initializes the command list panel by loading its CSS stylesheet.
     * If the stylesheet cannot be found, a warning is logged.
     */
    @FXML
    public void initialize() {

        String cssPath = "/view/stylesheets/CommandListPanel.css";
        URL cssResource = getClass().getResource(cssPath);

        if (cssResource != null) {
            String css = cssResource.toExternalForm();
            commandListView.getStylesheets().add(css);
        } else {
            logger.warning("Resource not found: " + cssPath);
        }
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CommandInfo}.
     */
    static class CommandListViewCell extends ListCell<CommandInfo> {
        @Override
        protected void updateItem(CommandInfo commandInfo, boolean empty) {
            super.updateItem(commandInfo, empty);

            if (empty || commandInfo == null) {
                setGraphic(null);
                setText(null);
            } else {
                HBox commandContainer = new HBox();
                commandContainer.getStyleClass().add("command-container");

                Label commandLabel = new Label(commandInfo.getCommand());
                commandLabel.getStyleClass().add("command-label");
                commandContainer.getChildren().add(commandLabel);

                Label descriptionLabel = new Label(": " + commandInfo.getDescription());
                descriptionLabel.getStyleClass().add("description-label");

                HBox container = new HBox(commandContainer, descriptionLabel);
                container.getStyleClass().add("container");

                setGraphic(container);
            }
        }
    }
}
