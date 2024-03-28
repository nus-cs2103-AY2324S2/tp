package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandMessageUsageUtil.generateMessageUsage;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_NUSNET_NOPREFIX;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delstu";

    public static final String MESSAGE_USAGE = generateMessageUsage(
            COMMAND_WORD,
            "Deletes the student identified by NUSNET_ID.",
            PARAMETER_NUSNET_NOPREFIX
    );

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final NusNet targetNusNet;

    public DeletePersonCommand(NusNet targetNusNet) {
        this.targetNusNet = targetNusNet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetNusNet == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        Person personToDelete = lastShownList.stream()
                .filter(person -> person.getNusNet().equals(targetNusNet))
                .findFirst()
                .orElse(null);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_MISSING_NUSNET);
        }

        assert(personToDelete != null);

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonCommand)) {
            return false;
        }

        DeletePersonCommand otherDeleteCommand = (DeletePersonCommand) other;

        return targetNusNet.equals(otherDeleteCommand.targetNusNet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNusNet", targetNusNet)
                .toString();
    }
}
