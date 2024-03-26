package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

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
    private ComboBox<Person> personComboBox;

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private StackPane schedulePanelPlaceholder;

    @FXML
    private WeeklyScheduleView weeklyScheduleView;

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

        weeklyScheduleView = new WeeklyScheduleView();
        populatePersonNameComboBox();
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
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        schedulePanelPlaceholder.getChildren().add(weeklyScheduleView.getRoot());

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
    }

    private void populatePersonNameComboBox() {
        // Example class names, replace with actual data retrieval logic
        ObservableList<Person> persons = logic.getFilteredPersonList();

        personComboBox.setItems(persons);

        // Use a cell factory to display the names of the Person objects
        personComboBox.setCellFactory(comboBox -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                setText(empty || person == null ? "" : person.getName().toString());
            }
        });

        personComboBox.setButtonCell(new ListCell<Person>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                setText(empty || person == null ? "" : person.getName().toString());
            }
        });

        ArrayList<Person> populatedPerson = new ArrayList<>();
        // Optional: Add a listener to react to selection changes
        personComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (populatedPerson.contains(newValue)) {
                    populatedPerson.remove(newValue);
                    System.out.println("Removed person: " + newValue.getName());
                    updateTableView(populatedPerson);
                } else {
                    if (populatedPerson.size() == 5) {
                        System.out.println("5 People have already been selected!");
                    } else {
                        populatedPerson.add(newValue);
                        System.out.println("Added person: " + newValue.getName());
                        updateTableView(populatedPerson);
                    }
                }
                // Call method to update UI based on selected person
                System.out.println("Current List of person: ");
                populatedPerson.forEach(person -> System.out.println(person.getName()));
            }
        });

        persons.addListener((ListChangeListener.Change<? extends Person> change) -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    List<? extends Person> removedPersons = change.getRemoved();
                    for (Person removedPerson : removedPersons) {
                        System.out.println("Removed person cause deleted: " + removedPerson.getName());
                        populatedPerson.remove(removedPerson);
                        System.out.println("Current List of person: ");
                        populatedPerson.forEach(person -> System.out.print(person.getName()));
                        updateTableView(populatedPerson);

                    }
                }
            }
        });
    }

    /**
     * Populate Person's Schedule into TableView to view
     * @param selectedPersons Array of Person to add to TableView
     */

    public void updateTableView(ArrayList<Person> selectedPersons) {
        // Clear the table view
        scheduleTable.getItems().clear();
        weeklyScheduleView.clear();
        // Loop through each selected person
        for (Person person : selectedPersons) {
            // Extract the schedules from the selected person
            LocalDate now = LocalDate.now();
            LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusDays(1); // End of Sunday
            ArrayList<Schedule> schedules = person.getSchedules();
            System.out.print("Got the deets!");
            ArrayList<Schedule> filteredSchedules = new ArrayList<>();
            for (Schedule sched : schedules) {
                LocalDateTime startTime = sched.getStartTime();
                LocalDateTime endTime = sched.getEndTime();
                if ((startTime.toLocalDate().isEqual(startOfWeek) || startTime.toLocalDate().isAfter(startOfWeek))
                        && (endTime.toLocalDate().isEqual(endOfWeek) || endTime.toLocalDate().isBefore(endOfWeek))) {
                    filteredSchedules.add(sched);
                }
            }
            // Add each schedule to the table view
            scheduleTable.getItems().addAll(filteredSchedules);
            weeklyScheduleView.populateWeeklySchedule(filteredSchedules);
        }
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
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
