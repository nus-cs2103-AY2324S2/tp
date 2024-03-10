package seedu.address.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private Text suggestionsText;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void handleTextChanged() {
        int caretPosition = commandTextField.getCaretPosition();
        String currentText = commandTextField.getText();
        List<String> suggestions = generateSuggestions(currentText);
        updateSuggestions(suggestions);
        Platform.runLater(() -> commandTextField.positionCaret(Math.min(caretPosition, currentText.length())));
    }
    private List<String> generateSuggestions(String commandText) {
        final List<String> COMMANDS = Arrays.asList(
                "add", "list", "edit", "find", "delete", "clear", "interest", "findInterest", "addSched", "exit", "help"
        );

        final List<String> OPTIONS = Arrays.asList(
                "n/", "p/", "e/", "a/", "t/", "from/", "to/"
        );

        final List<String> COMMON_KEYWORDS = Arrays.asList(
                "NAME", "PHONE_NUMBER", "EMAIL", "ADDRESS", "TAG", "INDEX", "KEYWORD", "MORE_KEYWORDS", "INTEREST", "MORE_INTEREST", "SCHEDULE_NAME", "DATE_TIME", "TIME"
        );
        List<String> suggestions = new ArrayList<>();
        List<String> tokens = Arrays.asList(commandText.split("\\s+"));
        // Check if the entered command matches any suggestions
        for (String command : COMMANDS) {
            if (command.startsWith(commandText)) {
                // If there's a match, replace the command text with the suggestion
                commandTextField.setText(tokens.get(0));
                // Move cursor to the end of the suggestion
                suggestions.add(command);
                break;
            }
        }
        return suggestions;
    }

    public void updateSuggestions(List<String> suggestions) {
        StringBuilder suggestionsBuilder = new StringBuilder();
        for (String suggestion : suggestions) {
            suggestionsBuilder.append(suggestion).append("\n");
        }
        suggestionsText.setText(suggestionsBuilder.toString());
    }
    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
