package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultPanel extends UiPart<Region> {

    private static final String FXML = "ResultPanel.fxml";

    @FXML
    private TextArea resultPanel;

    public ResultPanel() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultPanel.setText(feedbackToUser);
    }

}
