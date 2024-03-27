package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.application.Application;

/**
 * Adds an job application from a candidate to the list of job applications
 */
public class DeleteApplicationCommand extends Command {
    public static final String COMMAND_WORD = "delete_app";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a selected job application from "
            + "the list of job applications \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";
    public static final String DELETE_APPLICATION_SUCCESS = "Job application at entry number %1$d"
            + " successfully deleted!";

    private final Index index;

    /**
     * @param index of the job application in the filtered application list to delete the application
     */
    public DeleteApplicationCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownApplicationList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownApplicationList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        model.deleteApplication(lastShownApplicationList.get(index.getZeroBased()));

        return new CommandResult(String.format(DELETE_APPLICATION_SUCCESS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApplicationCommand)) {
            return false;
        }

        DeleteApplicationCommand otherDeleteApplicationCommand = (DeleteApplicationCommand) other;
        return index.equals(otherDeleteApplicationCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
