package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.model.AddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportCommand extends Command {
    
    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports all contacts currently shown.\n"
    + "Usually used after 'filter' or 'find' command to export a subset of contacts. \n"
    + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts have been exported!";

    private Path savePath = Paths.get("data", "exportedcontacts.json");

    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";


    /**
     * 
     * @param indexes string of index
     */
    public ExportCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        JsonAddressBookStorage storage = new JsonAddressBookStorage(savePath);
        AddressBook book = new AddressBook();
        book.setPersons(lastShownList);
        
        try {
            storage.saveAddressBook(book);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }
 


        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}
