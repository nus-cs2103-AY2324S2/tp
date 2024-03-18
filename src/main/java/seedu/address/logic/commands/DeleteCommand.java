package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed name from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "/delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their name.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " Moochie";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;

    public DeleteCommand(Name name) {
        this.targetName = name;
    }

    /**
     * Finds a person from a List of persons identified by its name.
     *
     * @param personList The list of persons to search from.
     * @param targetName The name of the person to return.
     *
     * @return The person object with name equals to {@code targetName}.
     * */
    public Person findByName(List<Person> personList, Name targetName) {
        for (Person person: personList) {
            Name name = person.getName();
            if (name.equals(targetName)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete;

        personToDelete = findByName(lastShownList, targetName);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);

    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
