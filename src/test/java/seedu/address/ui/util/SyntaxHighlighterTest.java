package seedu.address.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.util.CommandMessageUsageUtil.isUtilLabel;
import static seedu.address.logic.commands.util.CommandWordUtil.isCommandWord;
import static seedu.address.ui.util.SyntaxHighlighter.BOLD_STYLE_CLASS;
import static seedu.address.ui.util.SyntaxHighlighter.ERROR_STYLE_CLASS;
import static seedu.address.ui.util.SyntaxHighlighter.SUCCESS_STYLE_CLASS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.MarkAttendanceCommand;

class SyntaxHighlighterTest {
    private TextFlow errorTextFlow;
    private TextFlow successTextFlow;
    private TextFlow genericTextFlow;

    @BeforeEach
    void setUp() {
        SyntaxHighlighter s = new SyntaxHighlighter();
        errorTextFlow = s.generateLine(Messages.MESSAGE_UNKNOWN_COMMAND);
        successTextFlow = s.generateLine(Messages.MESSAGE_MARKED_ATTENDANCE_SUCCESS);
        genericTextFlow = s.generateLine(MarkAttendanceCommand.MESSAGE_USAGE);
    }

    @Test
    void generateLine_errorLine_singleText() {
        ObservableList<Node> nodes = errorTextFlow.getChildren();
        assertEquals(1, nodes.size());
    }

    @Test
    void generateLine_errorLine_errorFormat() {
        Node node = errorTextFlow.getChildren().get(0);
        if (node instanceof Text) {
            Text text = (Text) node;
            assertTrue(text.getStyleClass().contains(ERROR_STYLE_CLASS));
            assertTrue(text.getStyleClass().contains(BOLD_STYLE_CLASS));
        }
    }

    @Test
    void generateLine_successLine_singleText() {
        ObservableList<Node> nodes = successTextFlow.getChildren();
        assertEquals(1, nodes.size());
    }

    @Test
    void generateLine_successLine_successFormat() {
        Node node = successTextFlow.getChildren().get(0);
        if (node instanceof Text) {
            Text text = (Text) node;
            assertTrue(text.getStyleClass().contains(SUCCESS_STYLE_CLASS));
            assertTrue(text.getStyleClass().contains(BOLD_STYLE_CLASS));
        }
    }

    @Test
    void generateLine_genericLine_childrenText() {
        for (Node node : genericTextFlow.getChildren()) {
            assertTrue(node instanceof Text);
        }
    }

    @Test
    void generateLine_genericLine_keywordsBold() {
        for (Node node : genericTextFlow.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                String content = text.getText();
                assertEquals(
                        content.endsWith(":") && (isUtilLabel(content) || isCommandWord(content)),
                        text.getStyleClass().contains(BOLD_STYLE_CLASS)
                );
            }
        }
    }

    @Test
    void generateLine_genericLine_noInvalidFormats() {
        for (Node node : genericTextFlow.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                assertFalse(text.getStyleClass().contains(ERROR_STYLE_CLASS));
                assertFalse(text.getStyleClass().contains(SUCCESS_STYLE_CLASS));
            }
        }
    }
}
