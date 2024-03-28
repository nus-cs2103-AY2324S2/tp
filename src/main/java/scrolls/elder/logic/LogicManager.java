package scrolls.elder.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.logic.commands.Command;
import scrolls.elder.logic.commands.CommandResult;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.logic.parser.AddressBookParser;
import scrolls.elder.logic.parser.exceptions.ParseException;
import scrolls.elder.model.Model;
import scrolls.elder.model.person.Person;
import scrolls.elder.storage.Storage;

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

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDatastore(model.getDatastore());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getDatastore().getPersonStore().getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getFilteredVolunteerList() {
        return model.getDatastore().getPersonStore().getFilteredVolunteerList();
    }

    @Override
    public ObservableList<Person> getFilteredBefriendeeList() {
        return model.getDatastore().getPersonStore().getFilteredBefriendeeList();
    }

    @Override
    public Path getDatastoreFilePath() {
        return model.getDatastoreFilePath();
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
