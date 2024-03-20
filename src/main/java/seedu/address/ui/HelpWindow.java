package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-t17-3.github.io/tp/UserGuide.html";
    public static final String[][] COMMAND_DESCRIPTIONS = {
        {"help", "Shows this window."},
        {"add", "Adds a client to FitBook."},
        {"list", "Shows a list of all clients saved in FitBook."},
        {"edit", "Edits an existing client in FitBook."},
        {"note", "Adds a new note to a client."},
        {"find", "Finds all clients whose specified attribute contains the specified keyword."},
        {"delete", "Deletes the specified client from FitBook."},
        {"clear", "Clears all entries from FitBook. USE WITH CAUTION."},
        {"exit", "Exits FitBook."}
    };

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private VBox helpMessageContainer;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    private boolean isDesktopBrowseActionSupported() {
        return Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)
            && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
    }

    /**
     * Opens user guide URL in user's default browser.
     */
    @FXML
    private void openUrl() {
        try {
            if (isDesktopBrowseActionSupported()) {
                Desktop.getDesktop().browse(new URI(USERGUIDE_URL));
            } else {
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent url = new ClipboardContent();
                url.putString(USERGUIDE_URL);
                clipboard.setContent(url);
            }
        } catch (URISyntaxException | IOException e) {
            logger.warning("Something went wrong when opening user guide URL.");
        }
    }

    /**
     * Initializes a new HelpWindow.
     */
    public void initialize() {
        if (!isDesktopBrowseActionSupported()) {
            copyButton.setText("Copy URL");
        }

        for (String[] command : COMMAND_DESCRIPTIONS) {
            String commandName = command[0];
            String commandDescription = command[1];

            Label commandLabel = new Label(commandName);
            Label descriptionLabel = new Label(commandDescription);

            commandLabel.setPrefWidth(50);

            HBox commandBox = new HBox(10);
            commandBox.getChildren().addAll(commandLabel, descriptionLabel);

            helpMessageContainer.getChildren().add(commandBox);
        }
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                                                             <li>
     *                                                             if this method is called on a thread other than
     *                                                             the JavaFX Application Thread.
     *                                                             </li>
     *                                                             <li>
     *                                                             if this method is called during animation or
     *                                                             layout processing.
     *                                                             </li>
     *                                                             <li>
     *                                                             if this method is called on the primary stage.
     *                                                             </li>
     *                                                             <li>
     *                                                             if {@code dialogStage} is already showing.
     *                                                             </li>
     *                                                             </ul>
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
}
