package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the NusId used in the displayed person list.\n"
            + "Parameters: NusId (8 digits long, starting with an 'E'). \n"
            + "Example: " + COMMAND_WORD + " E0123456";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final NusId nusId;

    public DeleteCommand(NusId nusId) {
        this.nusId = nusId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToDelete = lastShownList.stream().filter(person -> person.getNusId().equals(nusId))
                .findFirst().orElse(null);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_NON_EXISTENT_PERSON);
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
        return nusId.equals(otherDeleteCommand.nusId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetnusId", nusId.toString())
                .toString();
    }
}
