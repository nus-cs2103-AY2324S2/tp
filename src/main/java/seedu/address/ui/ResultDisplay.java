package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String SUCCESS_STYLE_CLASS = "success";
    private static final String ERROR_STYLE_CLASS = "error";

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser, boolean isSuccess) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);

        // Remove any previous styles
        resultDisplay.getStyleClass().removeAll(SUCCESS_STYLE_CLASS, ERROR_STYLE_CLASS);

        // Add the appropriate style class based on the isSuccess flag
        if (isSuccess) {
            resultDisplay.getStyleClass().add(SUCCESS_STYLE_CLASS);
        } else {
            resultDisplay.getStyleClass().add(ERROR_STYLE_CLASS);
        }
    }

    // Keep the old method signature for backward compatibility if needed
    public void setFeedbackToUser(String feedbackToUser) {
        setFeedbackToUser(feedbackToUser, false);
    }

}
