package seedu.address.logic.commands;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LastContact;
import seedu.address.model.person.Person;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class LastContactCommand extends Command {

    public static final String COMMAND_WORD = "lastcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a last contacted tag to the "
            + "client identified.\n"
            + "Existing date and time tagged will be overwritten by the input.\n"
            + "Parameters: NAME (case in-sensitive), DATETIME (DD-MM-YYYY HHMM) format.\n"
            + "Example: lastcontact n/Cole lc/12-03-2024 1812";
    public static final String MESSAGE_ADD_LAST_CONTACTED_SUCCESS = "Tagged last contacted to client:\n%1$s";
    public static final String MESSAGE_ADD_LAST_CONTACTED_FAIL = "Client name not found:\n%1$s";
    private final String name;
    private final LastContact lastcontact;

    /**
     * Constructor the Last contact command
     * @param name of the person in the contact list
     */
    public LastContactCommand(String name, LastContact lastcontact) {
        requireAllNonNull(name);

        this.name = name;
        this.lastcontact = lastcontact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person currPerson : lastShownList) {
            if (currPerson.getName().toString().equalsIgnoreCase(this.name)) {
                Person editedPerson = new Person(currPerson.getName(), currPerson.getPhone(), currPerson.getEmail(),
                        currPerson.getAddress(), currPerson.getTags(), this.lastcontact);
                model.setPerson(currPerson, editedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                return new CommandResult(generateSuccessMessage(editedPerson));
            }
        }
        return new CommandResult(generateFailedMessage(name));
    }
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_LAST_CONTACTED_SUCCESS, Messages.format(personToEdit));
    }

    private String generateFailedMessage(String name) {
        return String.format(MESSAGE_ADD_LAST_CONTACTED_FAIL, name);
    }
}
