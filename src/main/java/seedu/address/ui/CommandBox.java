package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final int ZERO_INDEX = 0;
    private static final Logger logger = LogsCenter.getLogger(CommandBox.class);

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
        String keyName = event.getCode().getName();
        assert !keyName.isEmpty();

        //Performs nothing if there is no history.
        if (inputHistory.inputList.isEmpty()) {
            return ;
        }
        if (keyName.equals("Up")) {
            inputHistory.decrementIndex();
            setTextField();
        }
        if (keyName.equals("Down")) {
            inputHistory.incrementIndex();
            setTextField();
        }
    }

    /**
     * Sets the text field with the command, moves caret to the end of text.
     */
    private void setTextField() {
        String commandText = inputHistory.getCommand();
        commandTextField.setText(commandText);
        commandTextField.end();
        assert !commandText.isEmpty();
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

    /**
     * Encapsulates the input history of typed commands.
     */
    public class InputHistory {
        private ArrayList<String> inputList;
        private int currentIndex;

        /**
         * Constructor for InputHistory.
         */
        public InputHistory() {
            this.inputList = new ArrayList<>();
            this.currentIndex = ZERO_INDEX;
        }

        /**
         * Adds typed in command into inputList.
         * @param commandText Sting of command.
         */
        public void addToInputHistory(String commandText) {
            this.inputList.add(commandText);
            currentIndex = inputList.size();
        }

        /**
         * Increments the counter.
         */
        public void incrementIndex() {
            int maxListIndex = inputList.size() - 1;

            if (this.currentIndex < maxListIndex) {
                this.currentIndex++;
                logger.info("Showing succeeding command.");
            }
        }

        /**
         * Decrements the counter.
         */
        public void decrementIndex() {
            if (this.currentIndex != ZERO_INDEX) {
                this.currentIndex--;
                logger.info("Showing previous command.");
            }
        }

        /**
         * Retrieves the command corresponding to the counter.
         * @return String of command.
         */
        public String getCommand() {
            return this.inputList.get(currentIndex);
        }

    }

}
