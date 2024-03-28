package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.CommandBoxState;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.person.Person;

/**
 * Pushes the program into the state to delete the person at targetIndex.
 */
public class InitDeleteCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Delete person. Proceed? (Y/N)";
    private final Index targetIndex;


    public InitDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.setLastMentionedPerson(personToDelete);
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.DELETECONFIRM);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InitDeleteCommand)) {
            return false;
        }
        InitDeleteCommand o = (InitDeleteCommand) other;
        return (this.targetIndex.equals(o.targetIndex));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
