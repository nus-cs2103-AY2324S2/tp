package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.CommandBoxState;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.application.Application;

/**
 * Pushes the program into the state to delete the person at targetIndex.
 */
public class InitDeleteApplicationCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Delete application selected. Proceed? (Y/N)";
    private final Index targetIndex;


    public InitDeleteApplicationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownApplicationList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownApplicationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Application applicationToDelete = lastShownApplicationList.get(targetIndex.getZeroBased());
        model.setLastMentionedApplication(applicationToDelete);
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.DELETEAPPLICATIONCONFIRM);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InitDeleteApplicationCommand)) {
            return false;
        }
        InitDeleteApplicationCommand o = (InitDeleteApplicationCommand) other;
        return (this.targetIndex.equals(o.targetIndex));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
