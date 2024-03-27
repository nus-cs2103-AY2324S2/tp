package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hirehub.commons.util.ToStringBuilder;
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
    public static final String DELETE_APPLICATION_SUCCESS = "Job application for candidate \"%1$s\" "
            + "applying for job \"%2$s\" successfully deleted!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Application applicationToDelete = model.getLastMentionedApplication().get();
        model.deleteApplication(applicationToDelete);
        return new CommandResult(String.format(DELETE_APPLICATION_SUCCESS,
                applicationToDelete.getPerson().getName(), applicationToDelete.getJob().title));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteApplicationCommand)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
