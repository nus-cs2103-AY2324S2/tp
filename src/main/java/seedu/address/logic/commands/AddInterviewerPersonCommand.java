package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds an interviewer to the talent tracker.
 */
public class AddInterviewerPersonCommand extends AddPersonCommand {

    public static final String COMMAND_WORD = AddPersonCommand.COMMAND_WORD + "_interviewer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interviewer to the talent tracker. "
            + AddPersonCommand.MESSAGE_USAGE;

    public static final String MESSAGE_SUCCESS = "New interviewer added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This interviewer already exists in the talent tracker";

    /**
     * Creates an AddInterviewerCommand to add the specified {@code Person}
     */
    public AddInterviewerPersonCommand(Person person) {
        super(person);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterviewerPersonCommand)) {
            return false;
        }

        AddPersonCommand otherAddPersonCommand = (AddPersonCommand) other;
        return toAdd.equals(otherAddPersonCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
