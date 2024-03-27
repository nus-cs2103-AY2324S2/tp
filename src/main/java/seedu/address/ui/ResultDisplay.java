package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.util.SyntaxHighlighter;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private static final SyntaxHighlighter resultSyntax = new SyntaxHighlighter("result-display");

    @FXML
    private VBox resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);

        resultDisplay.getChildren().setAll(Arrays
                .stream(feedbackToUser.split("\\n"))
                .map(String::strip)
                .map(resultSyntax::generateLine)
                .collect(Collectors.toList())
        );
    }
}
