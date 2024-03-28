package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the contacts currently on displayed to an external json file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports all contacts currently shown.\n"
        + "Usually used after 'filter' or 'find' command to export a subset of contacts. \n"
        + "Parameters: FILE_NAME (cannot be HRConnect or ArchivedApplicants)"
        + "Example: " + COMMAND_WORD + " NEW_APPLICANTS";

    public static final String MESSAGE_SUCCESS = "Contacts have been exported!";

    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    private static final String HRCONNECT = "HRConnect";

    private static final String ARCHIVED_APPLICANTS = "ArchivedApplicants";

    private static final String EMPTY = "";

    private Path savePath;

    private String fileName;

    /**
     * Constructor for export command
     * @param fileName name of file where contacts are to be saved
     */
    public ExportCommand(String fileName) {
        requireNonNull(fileName);
        this.fileName = fileName.trim();
        savePath = Paths.get("data", fileName + ".json");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        JsonAddressBookStorage storage = new JsonAddressBookStorage(savePath);
        AddressBook book = new AddressBook();
        book.setPersons(lastShownList);

        if (fileName.equals(HRCONNECT) || fileName.equals(ARCHIVED_APPLICANTS)) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILE_NAME);
        }

        if (fileName.equals(EMPTY)) {
            throw new CommandException(Messages.MESSAGE_EMPTY_FILE_NAME);
        }

        try {
            storage.saveAddressBook(book);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}
