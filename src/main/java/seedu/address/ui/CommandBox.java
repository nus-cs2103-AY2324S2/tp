package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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
            int index = extractIndex(commandText);

            CommandResult result = commandExecutor.execute(commandText);
            if (commandText.contains("!")) {
                int exclamationIndex = commandText.indexOf('!');
                char letterAfterExclamation = commandText.charAt(exclamationIndex + 1);

                String prefix;
                switch (letterAfterExclamation) {
                case 'n':
                    prefix = "name";
                    break;
                case 'p':
                    prefix = "phone";
                    break;
                case 'e':
                    prefix = "email";
                    break;
                case 'a':
                    prefix = "address";
                    break;
                case 't':
                    prefix = "tag";
                    break;
                case 'g':
                    prefix = "grade";
                    break;
                default:
                    prefix = "";
                    break;
                }

                commandTextField.setText("edit " + index + " " + prefix + ": " + result.getFeedbackToUser());
            } else {
                commandTextField.setText("");
            }
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        } finally {
            // Move the cursor to the rightmost position
            commandTextField.end();
        }
    }

    private int extractIndex(String commandText) throws ParseException {
        String[] parts = commandText.split("\\s+"); // Split by whitespace
        if (parts.length < 2) {
            throw new ParseException("Invalid command format. Expected 'edit <index> !<type>'.");
        }

        try {
            return Integer.parseInt(parts[1]); // Assuming index is the second element
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid index format. Expected a number.");
        }
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
