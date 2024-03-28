package seedu.address.ui;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-w11-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more information, refer to this user guide: ";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private VBox commandList; // Ensure this matches the fx:id of your CommandList VBox

    @FXML
    private StackPane commandListPanelPlaceholder; // Placeholder for dynamic content if needed

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. It instantiates the CommandListPanel
     * and adds it to the placeholder stack pane in the Help window.
     */
    @FXML
    private void initialize() {
        CommandListPanel commandListPanel = new CommandListPanel();
        commandListPanelPlaceholder.getChildren().add(commandListPanel.getRoot());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens the specified URL in the user's default web browser.
     * This method checks if the Desktop class supports the BROWSE action.
     * If supported, it attempts to open the given URL in the default browser.
     * Logs an error if browsing is not supported or if an exception occurs.
     */
    @FXML
    private void openUrlInBrowser() {
        try {
            URI uri = new URI(USERGUIDE_URL);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.err.println("Browsing not supported!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

