package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


import java.util.ArrayList;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final int ZERO_INDEX = 0;

    private final CommandExecutor commandExecutor;

    private InputHistory inputHistory;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.inputHistory = new InputHistory();
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
        } finally {
            System.out.println(inputHistory.count);
            inputHistory.addToInputHistory(commandText);
            commandTextField.setText("");
        }
    }

    /**
     * Handles the Up/Down key pressed event.
     * @param event The event when a key is pressed.
     */
    @FXML
    private void handleArrowKey(KeyEvent event) {
        if (event.getCode().getName().equals("Up")) {
            inputHistory.decrementCount();
            commandTextField.setText(inputHistory.getCommand());
        }
        if (event.getCode().getName().equals("Down")) {
            inputHistory.incrementCount();
            commandTextField.setText(inputHistory.getCommand());
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

    public class InputHistory {
        ArrayList<String> inputList;
        int count;

        public InputHistory() {
            this.inputList = new ArrayList<>();
            this.count = ZERO_INDEX;
        }

        public void addToInputHistory(String commandText) {
            this.inputList.add(commandText);
            this.count++;
        }
        public void incrementCount() {
            int maxListIndex = inputList.size() - 1;
            if (this.count < maxListIndex) {
                this.count++;
            }
        }

        public void decrementCount() {
            if (this.count != ZERO_INDEX) {
                this.count--;
            }
        }

        public String getCommand() {
            return this.inputList.get(count);
        }

    }

}
