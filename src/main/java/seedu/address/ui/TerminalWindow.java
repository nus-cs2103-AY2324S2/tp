package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a terminal window.
 */
public class TerminalWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(TerminalWindow.class);
    private static final String FXML = "TerminalWindow.fxml";

    @FXML
    private TextArea outputArea;

    @FXML
    private TextField inputField;

    /**
     * Creates a new TerminalWindow.
     *
     * @param root Stage to use as the root of the TerminalWindow.
     */
    public TerminalWindow(Stage root) {
        super(FXML, root);
        // Initialize your Terminal window here if needed
    }

    /**
     * Creates a new TerminalWindow.
     */
    public TerminalWindow() {
        this(new Stage());
    }

    /**
     * Shows the terminal window.
     */
    public void show() {
        logger.fine("Showing terminal window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the terminal window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the terminal window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the terminal window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        inputField.setOnAction(event -> {
            String command = inputField.getText();
            // Process the command and append to outputArea
            outputArea.appendText(">" + command + "\n");
            // Placeholder for actual command processing
            outputArea.appendText("Command output goes here...\n");
            inputField.clear();
        });
    }
}
