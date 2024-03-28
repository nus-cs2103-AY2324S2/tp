package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.autocomplete.AutoComplete;
import seedu.address.logic.autocomplete.AutoCompleteCommand;
import seedu.address.logic.autocomplete.AutoCompleteNusNetId;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseName;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";
    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT = "Could not save data to file %s due to "
        + "insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        this.initialize();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = addressBookParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        // Update the autocomplete for the NUSNET IDs if the command
        // potentially modifies the NUSNET IDs
        if (command instanceof AddPersonCommand
            || command instanceof DeletePersonCommand
            || command instanceof EditPersonCommand) {

            AutoCompleteNusNetId.update(
                getAddressBook()
                    .getPersonList()
                    .stream()
                    .map(person -> person.getNusNet().value)
                    .toArray(String[]::new));
        }

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveCourse(model.getCourseName());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public String autoComplete(String commandText) {
        AutoComplete ac = addressBookParser.parseAutoComplete(commandText);
        assert ac != null;
        return ac.getAutoComplete(commandText);
    }

    /**
     * Initializes various miscellaneous initializations for the logic manager.
     */
    private void initialize() {
        // Initialize the autocomplete for the commands
        AutoCompleteCommand.initialize(
            AddPersonCommand.COMMAND_WORD,
            EditPersonCommand.COMMAND_WORD,
            DeletePersonCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            FindPersonCommand.COMMAND_WORD,
            ListPersonCommand.COMMAND_WORD,
            MarkAttendanceCommand.COMMAND_WORD,
            UnmarkAttendanceCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD);

        // Initialize the autocomplete for the NUSNET IDs
        AutoCompleteNusNetId.initialize(
            getAddressBook()
                .getPersonList()
                .stream()
                .map(person -> person.getNusNet().value)
                .toArray(String[]::new));
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyCourseName getCourseName() {
        return model.getCourseName();
    }

    @Override
    public Path getCourseNameFilePath() {
        return model.getCourseNameFilePath();
    }
}
