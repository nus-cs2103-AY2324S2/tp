package seedu.hirehub.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.hirehub.commons.core.GuiSettings;
import seedu.hirehub.commons.core.LogsCenter;
import seedu.hirehub.logic.commands.Command;
import seedu.hirehub.logic.commands.CommandResult;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.logic.parser.AddressBookParser;
import seedu.hirehub.logic.parser.ClearConfirmationStageParser;
import seedu.hirehub.logic.parser.DeleteApplicationConfirmationStageParser;
import seedu.hirehub.logic.parser.DeleteConfirmationStageParser;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.ReadOnlyAddressBook;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final ClearConfirmationStageParser clearConfirmationStageParser;
    private final DeleteConfirmationStageParser deleteConfirmationStageParser;
    private final DeleteApplicationConfirmationStageParser deleteApplicationConfirmationStageParser;
    private CommandBoxState state;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        clearConfirmationStageParser = new ClearConfirmationStageParser();
        deleteConfirmationStageParser = new DeleteConfirmationStageParser();
        deleteApplicationConfirmationStageParser = new DeleteApplicationConfirmationStageParser();
        this.state = CommandBoxState.NORMAL;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = null;
        switch (state) {
        case NORMAL:
            command = addressBookParser.parseCommand(commandText);
            break;
        case CLEARCONFIRM:
            command = clearConfirmationStageParser.parseCommand(commandText);
            break;
        case DELETECONFIRM:
            command = deleteConfirmationStageParser.parseCommand(commandText);
            break;
        case DELETEAPPLICATIONCONFIRM:
            command = deleteApplicationConfirmationStageParser.parseCommand(commandText);
            break;
        default:
            break;
        }
        assert (command != null);
        commandResult = command.execute(model);
        state = commandResult.getCommandBoxState();

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
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
    public ObservableList<Job> getFilteredJobList() {
        return model.getFilteredJobList();
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
}
