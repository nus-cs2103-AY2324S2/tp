package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;



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

    public static final Map<String, String> COMMAND_FORMAT_MAP = new HashMap<>();

    static {
        COMMAND_FORMAT_MAP.put("add", "n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...");
        COMMAND_FORMAT_MAP.put("list", "");
        COMMAND_FORMAT_MAP.put("edit", "INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…");
        COMMAND_FORMAT_MAP.put("find", "KEYWORD [MORE_KEYWORDS]");
        COMMAND_FORMAT_MAP.put("delete", "INDEX");
        COMMAND_FORMAT_MAP.put("clear", "");
        COMMAND_FORMAT_MAP.put("interest", "INDEX INTEREST [MORE_INTEREST]");
        COMMAND_FORMAT_MAP.put("findinterest", "INTEREST [MORE_INTEREST]");
        COMMAND_FORMAT_MAP.put("addsched", "INDEX [MORE_INDEX] SCHEDULE_NAME from/DATE_TIME to/TIME");
        COMMAND_FORMAT_MAP.put("exit", "");
        COMMAND_FORMAT_MAP.put("help", "");
    }

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

    /**
     * Handles the keyTyped event.
     */
    @FXML
    private void handleTextChanged() {
        suggestionsText.setText("");
        String currentText = commandTextField.getText().toLowerCase();
        List<String> suggestions = generateSuggestions(currentText);
        updateSuggestions(suggestions);
    }

    /**
     * Generates suggestions from text inputs
     */
    private List<String> generateSuggestions(String commandText) {
        List<String> suggestions = new ArrayList<>();
//        for (Map.Entry<String, String> entry : COMMAND_FORMAT_MAP.entrySet()) {
//            String command = entry.getKey();
//            String format = entry.getValue();
//            if (command.equals(commandText)) {
//                continue;
//            }
//            // Check if the command starts with or contains the current input text
//            if (command.startsWith(commandText) || command.contains(commandText)) {
//                // Add the command and its format to suggestions
//                String suggestion;
//                if (format.isEmpty()) {
//                    suggestion = command;
//                } else {
//                    suggestion = command + " - " + format;
//                }
//                suggestions.add(suggestion);
//            }
//        }
        final List<String> commandList = Arrays.asList(
                "add", "list", "edit", "find", "delete", "clear", "interest", "findinterest", "addsched", "exit", "help"
        );

        // Check if the entered command matches any suggestions
        for (String command : commandList) {
            if (command.startsWith(commandText) || command.contains(commandText)) {
                suggestions.add(command);
            }
        }
        suggestions.removeIf(suggest -> suggest.equals(commandText));
        return suggestions;
    }

    /**
     * Populate the suggestionText with generated suggestions
     */

    public void updateSuggestions(List<String> suggestions) {
        StringBuilder suggestionsBuilder = new StringBuilder();
        for (String suggestion : suggestions) {
            suggestionsBuilder.append(suggestion).append(" ");
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
