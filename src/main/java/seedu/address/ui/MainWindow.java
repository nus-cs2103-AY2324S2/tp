package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.zxing.WriterException;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ResetDebtCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final Double PERSON_LIST_RATIO = 0.25;
    private static final Integer MINIMUM_HEIGHT = 700;
    private static final Integer MINIMUM_WIDTH = 700;
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/friendfolio_logo.png"));
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private PaymentWindow paymentWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private ImageView logoImage;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());

        personListPanel.getPersonListView().prefWidthProperty().bind(Bindings.createDoubleBinding(()
                        -> personListPanelPlaceholder.getScene().getWidth() * PERSON_LIST_RATIO,
                personListPanelPlaceholder.getScene().widthProperty()));
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        logoImage.setImage(logo);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
        primaryStage.setMinHeight(MINIMUM_HEIGHT);
        primaryStage.setMinWidth(MINIMUM_WIDTH);
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
        if (paymentWindow != null) {
            paymentWindow.hide();
        }
    }

    @FXML
    private void handlePayment(Person person) {
        requireNonNull(person);
        if (paymentWindow != null) {
            paymentWindow.hide();
        }
        try {
            paymentWindow = new PaymentWindow(person, () -> {
                paymentWindow.hide();
                paymentWindow = null;
                try {
                    execute(new ResetDebtCommand(person));
                } catch (CommandException e) {
                    return;
                }
            });
            paymentWindow.show();
        } catch (IOException | WriterException e) {
            logger.info("An error occurred while trying to set up PaymentWindow: " + e.getMessage());
            resultDisplay.setFeedbackToUser("An error occurred while trying to set up PaymentWindow!");
            throw new RuntimeException(e.getMessage());
        }
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            handleCommandResult(commandResult);
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private CommandResult execute(Command command) throws CommandException {
        try {
            CommandResult commandResult = logic.execute(command);
            handleCommandResult(commandResult);
            return commandResult;
        } catch (CommandException e) {
            logger.info("An error occurred while executing command: " + command);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void handleCommandResult(CommandResult commandResult) {
        logger.info("Result: " + commandResult.getFeedbackToUser());
        resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

        if (commandResult.isShowHelp()) {
            handleHelp();
        }

        if (commandResult.isExit()) {
            handleExit();
        }

        if (commandResult.isShowPayment()) {
            assert (commandResult.getPersonToPay() != null);
            handlePayment(commandResult.getPersonToPay());
        }
    }
}
