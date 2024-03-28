package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.List;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.application.Application;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.status.Status;

/**
 * Updates recruitment status for candidates in the address book.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update status for an application within "
            + "the application list to one of the following 5 statuses:\n"
            + "PRESCREEN, IN_PROGRESS, WAITLIST, ACCEPTED, REJECTED\n"
            + "Parameters: e/EMAIL ti/jobTitle s/Status \n"
            + "Example: " + COMMAND_WORD + " e/acekhoon@gmail.com ti/Quantitative Trader s/ACCEPTED";

    public static final String MESSAGE_STATUS_PERSON_SUCCESS = "Status of Candidate Successfully"
            + " Updated to %1$s";

    public static final String MESSAGE_APPLICATION_NOT_FOUND = "Provided candidate who applied for Job \"%1$s\" not"
            + " found in the list of available applications";

    public static final String MESSAGE_DUPLICATE_STATUS = "This candidate with identical recruitment status %1$s "
            + "already exists in the application list";
    private final Email email;
    private final String jobTitle;
    private final Status status;

    /**
     * Creates an StatusCommand to update the candidate status for specified {@code Person}
     */
    public StatusCommand(Email email, String jobTitle, Status status) {
        requireNonNull(email);
        requireNonNull(jobTitle);
        requireNonNull(status);
        this.email = email;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Application applicationToUpdate = findApplication(model);

        if (status.equals(applicationToUpdate.status)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STATUS, status));
        }

        Application editedApplication = new Application(applicationToUpdate.getPerson(),
                applicationToUpdate.getJob(), status);

        model.setApplication(applicationToUpdate, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_STATUS_PERSON_SUCCESS, status));
    }

    /**
     * Finds an application with the given job title and the email address of candidate
     * @param model to retrieve the filtered application list
     */
    public Application findApplication(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownApplicationList = model.getFilteredApplicationList();
        Job jobToFind = new Job(jobTitle, "", 10);

        for (Application app : lastShownApplicationList) {
            if (email.equals(app.getPerson().getEmail()) && jobToFind.isSameJob(app.getJob())) {
                return app;
            }
        }

        throw new CommandException(String.format(MESSAGE_APPLICATION_NOT_FOUND, jobTitle));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        StatusCommand otherStatusCommand = (StatusCommand) other;
        return email.equals(otherStatusCommand.email)
                && jobTitle.equals(otherStatusCommand.jobTitle)
                && status.equals(otherStatusCommand.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("email", email)
                .add("jobTitle", jobTitle)
                .add("status", status)
                .toString();
    }
}
