package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.address.AddCommandHelper;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * A side window. Prompts the user to key in the fields one bye one
 * when they wish to add a person into address book but is unsure
 * of the exact format of the command.
 */
public class CommandHelperWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private AddCommandHelper addCommandHelper;



    /**
     * Starts the CommandHelper Window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setCommandHelper(AddCommandHelper a) {
        this.addCommandHelper = a;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello, please enter the name of the person"));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = addCommandHelper.getResponse(input);
        } catch (ParseException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response));
        userInput.clear();
    }


}
