package seedu.address.ui;

import static seedu.address.commons.util.UiUtil.setShortcut;

import java.util.Stack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import seedu.address.commons.util.UiUtil;
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
    private final Stack<String> undoStack;
    private final Stack<String> redoStack;

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

        undoStack = new Stack<>();
        redoStack = new Stack<>();

        setShortcut(getRoot(), KeyCode.UP, (keyCode) -> {
            undo();
        });
        setShortcut(getRoot(), KeyCode.DOWN, (keyCode) -> {
            redo();
        });
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

            undoStack.push(commandText);
            redoStack.clear();

            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
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

    private void undo() {
        if (!undoStack.isEmpty()) {
            String text = undoStack.pop();

            String currentText = commandTextField.getText();
            redoStack.push(currentText);

            UiUtil.setText(commandTextField, text);
        }
    }

    private void redo() {
        if (!redoStack.isEmpty()) {
            String text = redoStack.pop();

            String currentText = commandTextField.getText();
            undoStack.push(currentText);

            UiUtil.setText(commandTextField, text);
        } else {
            // This code block allows users to continue redoing until thye can an empty string, while still being able
            // to undo from there
            String currentText = commandTextField.getText();
            undoStack.push(currentText);

            commandTextField.setText(""); // Set as empty if there is nothing to redo
        }
    }
}
