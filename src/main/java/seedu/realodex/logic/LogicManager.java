package seedu.realodex.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.realodex.commons.core.GuiSettings;
import seedu.realodex.commons.core.LogsCenter;
import seedu.realodex.logic.commands.Command;
import seedu.realodex.logic.commands.CommandResult;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.logic.parser.RealodexParser;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.Model;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.person.Person;
import seedu.realodex.storage.Storage;

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
    private final RealodexParser realodexParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        realodexParser = new RealodexParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = realodexParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveRealodex(model.getRealodex());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyRealodex getRealodex() {
        return model.getRealodex();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getRealodexFilePath() {
        return model.getRealodexFilePath();
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
