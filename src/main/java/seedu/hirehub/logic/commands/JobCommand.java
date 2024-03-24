package seedu.hirehub.logic.commands;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.job.Job;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_VACANCY;



public class JobCommand extends Command {
    public static final String COMMAND_WORD = "job";
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

    public JobCommand(Job job) {
        this.job = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasJob(job)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(job);
        // TODO: remove print statements once UI is up and running
        for (Job j: model.getFilteredJobList()) {
            System.out.println(j);
        }
        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, job));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobCommand)) {
            return false;
        }

        JobCommand otherJobCommand = (JobCommand) other;
        return job.equals(otherJobCommand.job);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("job", job)
                .toString();
    }

}
