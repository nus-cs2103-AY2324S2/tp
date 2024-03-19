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

        commandTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // When the text field is focused
                if (commandTextField.getText().equals("> ")) {
                    commandTextField.setText("");
                }
            } else { // When the text field loses focus
                if (commandTextField.getText().isEmpty()) {
                    commandTextField.setText("> ");
                }
            }
        });

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.startsWith("> ")) {
                commandTextField.setText("> " + newValue.replaceAll("^> ?", ""));
                commandTextField.positionCaret(commandTextField.getText().length()); // Move the caret to the end
            }
            setStyleToDefault();
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            // Extract the actual command text entered by the user.
            String commandText = commandTextField.getText().substring(2).trim();

            if (!commandText.isEmpty()) {
                commandExecutor.execute(commandText);

                // Clear the command input only if the command is successful.
                commandTextField.setText("> ");
            }
        } catch (CommandException | ParseException e) {
            // If there's an error, indicate command failure without clearing the text
            setStyleToIndicateCommandFailure();
            commandTextField.positionCaret(commandTextField.getText().length());
        } finally {
            commandTextField.positionCaret(commandTextField.getText().length());
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

        // Just add the error style class, do not clear the text
        if (!styleClass.contains(ERROR_STYLE_CLASS)) {
            styleClass.add(ERROR_STYLE_CLASS);
        }

        // Keep the caret at the end of the text
        commandTextField.positionCaret(commandTextField.getText().length());
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
