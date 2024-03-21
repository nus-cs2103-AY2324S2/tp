package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;

import java.util.List;


public class AddDescriptionCommand extends Command {

    public static final String COMMAND_WORD = "description";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add description. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DESCRIPTION + "DESCRIPTION";

    public static final String MESSAGE_ADD_SUCCESS = "New description added to Person: %1$s";
    public static final String MESSAGE_DELETE_SUCCESS = "Description removed from Person: %1$s";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Description: %2$s";

    private final Description description;
    private final Index index;
    /**
     * Creates an AddAttendanceRecord to add the specified {@code date}
     */
    public AddDescriptionCommand(Index index, Description description) {
        requireAllNonNull(description);

        this.index = index;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getStudentId(), personToEdit.getAttendances(), description);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(!description.value.isEmpty() ? MESSAGE_ADD_SUCCESS : MESSAGE_DELETE_SUCCESS,
                personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddDescriptionCommand)) {
            return false;
        }

        AddDescriptionCommand otherAddDescriptionCommand = (AddDescriptionCommand) other;
        return description.equals(otherAddDescriptionCommand.description);
    }
}
