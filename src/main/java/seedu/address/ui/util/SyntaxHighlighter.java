package seedu.address.ui.util;

import static seedu.address.logic.Messages.isErrorMessage;
import static seedu.address.logic.Messages.isSuccessMessage;
import static seedu.address.logic.commands.util.CommandMessageUsageUtil.isUtilLabel;
import static seedu.address.logic.commands.util.CommandWordUtil.isCommandWord;

import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Container for methods regarding the highlighting of syntax.
 */
public class SyntaxHighlighter {
    public static final String BOLD_STYLE_CLASS = "bold";
    public static final String ERROR_STYLE_CLASS = "error";
    public static final String SUCCESS_STYLE_CLASS = "success";

    private final String[] styleClasses;

    /**
     * Creates a SyntaxHighlighter with default style classes to apply.
     *
     * @param styleClasses The style classes to add into rich-text formatting.
     */
    public SyntaxHighlighter(String... styleClasses) {
        this.styleClasses = styleClasses;
    }

    /**
     * Generates a rich-text line with basic syntax highlighting applied to it.
     * <p>
     * The formatting applied includes:
     * <li><b>Bolding of keywords</b>
     * <li><font color="#d06651">Error message highlighting</font>
     * <li><font color="#8AB68D">Success message highlighting</font>
     *
     * @param line The line of text to generate from.
     * @return The generated rich-text with formatting applied.
     */
    public TextFlow generateLine(String line) {
        if (isErrorMessage(line)) {
            return generateErrorLine(line);
        }

        if (isSuccessMessage(line)) {
            return generateSuccessLine(line);
        }

        return generateGenericLine(line);
    }

    /**
     * Generates a rich-text line with error formatting applied.
     */
    private TextFlow generateErrorLine(String errorMessage) {
        Text errorText = generateDefaultStyleText(errorMessage);
        errorText.getStyleClass().addAll(ERROR_STYLE_CLASS, BOLD_STYLE_CLASS);

        return new TextFlow(errorText);
    }

    /**
     * Generates a rich-text line with success formatting applied.
     */
    private TextFlow generateSuccessLine(String successMessage) {
        Text successText = generateDefaultStyleText(successMessage);
        successText.getStyleClass().addAll(SUCCESS_STYLE_CLASS, BOLD_STYLE_CLASS);

        return new TextFlow(successText);
    }

    /**
     * Generates a rich-text line with formatting applied.
     */
    private TextFlow generateGenericLine(String genericMessage) {
        String[] messageWords = genericMessage.split(" ");
        Node[] generatedNodes = new Node[messageWords.length * 2 - 1];

        for (int j = 0, i = 0; i < messageWords.length && j < generatedNodes.length; i++) {
            generatedNodes[j++] = generateGenericWord(messageWords[i]);
            if (j < generatedNodes.length) {
                generatedNodes[j++] = generateSingleSpace();
            }
        }

        return new TextFlow(generatedNodes);
    }

    /**
     * Generates a rich-text word with formatting applied.
     */
    private Text generateGenericWord(String word) {
        Text wordText = generateDefaultStyleText(word);

        if (word.isBlank()) {
            return wordText;
        }

        // Bold keywords
        if (isKeyword(word)) {
            wordText.getStyleClass().add(BOLD_STYLE_CLASS);
        }

        return wordText;
    }

    /**
     * Generates rich-text with the default styles applied to it, from a piece of text.
     */
    private Text generateDefaultStyleText(String text) {
        Text styledText = new Text(text);
        styledText.getStyleClass().addAll(styleClasses);
        return styledText;
    }

    private Text generateSingleSpace() {
        return generateDefaultStyleText(" ");
    }

    private boolean isKeyword(String text) {
        return text.endsWith(":") && (isUtilLabel(text) || isCommandWord(text));
    }
}
