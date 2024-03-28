package staffconnect.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import staffconnect.commons.core.GuiSettings;
import staffconnect.commons.core.LogsCenter;
import staffconnect.logic.commands.Command;
import staffconnect.logic.commands.CommandResult;
import staffconnect.logic.commands.exceptions.CommandException;
import staffconnect.logic.parser.StaffConnectParser;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.Model;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.person.Person;
import staffconnect.storage.Storage;

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
    private final StaffConnectParser staffConnectParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        staffConnectParser = new StaffConnectParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = staffConnectParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStaffBook(model.getStaffBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStaffBook getStaffBook() {
        return model.getStaffBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getStaffConnectFilePath() {
        return model.getStaffConnectFilePath();
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
