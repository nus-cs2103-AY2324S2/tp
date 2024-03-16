package seedu.address.logic.commands;

import java.nio.file.Path;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORT;

/**
 * Changes the  of an existing person in the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remark command not implemented yet";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports contacts from specified filepath "
            + "Existing contacts will.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_IMPORT + "import] "
            + "Example: " + COMMAND_WORD + PREFIX_IMPORT + "C:usr/lib/text.csv";
    private static final String MESSAGE_ARGUMENTS = "filePath: %s";
    private static final String MESSAGE_IMPORT_SUCCESS = "Imported Contacts from: %s";
    private static final String MESSAGE_DATA_LOAD_ERROR = "Unable to load data from %s";
    private final Path filePath;

    /**
     * @param filePath absolute path of file (path starts from C:...)
     */
    public ImportCommand(Path filePath) {
        requireAllNonNull(filePath);

        this.filePath = filePath;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        String newJsonFilePath = "data/importedAddressBook.json";
        // convert csv file at filePath to json file
        CsvUtil.convertToJSON(filePath, newJsonFilePath);
        // convert json file to addressBook
        try {
            AddressBookStorage newAddressBookStorage = new JsonAddressBookStorage(ParserUtil.parseFilePath(newJsonFilePath));
            model.setAddressBook(
                    newAddressBookStorage
                            .readAddressBook()
                            .orElseGet(SampleDataUtil::getSampleAddressBook));
        } catch (DataLoadingException e) {
            throw new CommandException(String.format(MESSAGE_DATA_LOAD_ERROR, filePath));
        } catch (ParseException e) {
            throw new CommandException(String.format(MESSAGE_DATA_LOAD_ERROR, newJsonFilePath));
        }

        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, filePath));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand e = (ImportCommand) other;
        return filePath.equals(e.filePath);
    }

}
