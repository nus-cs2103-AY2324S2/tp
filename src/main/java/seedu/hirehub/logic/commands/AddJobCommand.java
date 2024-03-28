package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_VACANCY;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.job.Job;


/**
 * Adds a job to the list of open jobs
 */
public class AddJobCommand extends Command {
    public static final String COMMAND_WORD = "add_job";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new job to list of open jobs. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + PREFIX_VACANCY + "VACANCY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Software Engineer "
            + PREFIX_DESCRIPTION + "Must be proficient in C++ "
            + PREFIX_VACANCY + "10\n";
    public static final String MESSAGE_ADD_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the list of open jobs";

    private final Job job;

    public AddJobCommand(Job job) {
        this.job = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasJob(job)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(job);
        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, job));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddJobCommand)) {
            return false;
        }

        AddJobCommand otherJobCommand = (AddJobCommand) other;
        return job.equals(otherJobCommand.job);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("job", job)
                .toString();
    }

}
