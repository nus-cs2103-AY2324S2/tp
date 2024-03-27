package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.application.Application;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.model.status.Status;

/**
 * Adds an job application from a candidate to the list of job applications
 */
public class AddApplicationCommand extends Command {
    public static final String COMMAND_WORD = "add_app";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new job application "
            + "from a candidate to the list of applications.\n"
            + "Parameters: "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "acekhoon@gmail.com "
            + PREFIX_TITLE + "Quantitative Researcher ";
    public static final String MESSAGE_ADD_SUCCESS = "New job application added successfully! Candidate \"%1$s\" "
            + "applied for the job \"%2$s\"";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This candidate already applied for this job "
            + "and application has been processed already into the list";
    public static final String MESSAGE_NO_PERSON_IN_LIST = "This candidate is not in the current list of candidates! ";
    public static final String MESSAGE_NO_JOB_IN_LIST = "This job has not been created";

    private final Email email;
    private final String jobTitle;
    private final Status status;

    /**
     * @param email of the person in the filtered person list to add a job application for
     * @param jobTitle of the job to match a candidate with the job they applied for
     * @param status of the person to show the current recruitment status of a candidate
     */
    public AddApplicationCommand(Email email, String jobTitle, Status status) {
        requireNonNull(email);
        requireNonNull(jobTitle);
        requireNonNull(status);
        this.email = email;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    /**
     * Overloads the constructor where the status is set as "PRESCREEN" as a default if it is not
     * provided by the recruiter
     */
    public AddApplicationCommand(Email email, String jobTitle) {
        this(email, jobTitle, new Status("PRESCREEN"));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personMatchingEmail = findPersonMatchingEmail(email, model);
        Job jobMatchingTitle = findJobMatchingTitle(jobTitle, model);

        Application newCandidateApplication = new Application(personMatchingEmail, jobMatchingTitle, status);

        if (model.hasApplication(newCandidateApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(newCandidateApplication);

        // TODO: remove print statements once UI is up and running
        for (Application app: model.getFilteredApplicationList()) {
            System.out.println(app);
        }

        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, personMatchingEmail.getName(), jobTitle));
    }

    /**
     * Finds a person with the given email in the filtered person list
     * @param email for email of a candidate to search for in the list
     * @param model to retrieve the filtered person list
     */
    public Person findPersonMatchingEmail(Email email, Model model) throws CommandException {
        requireNonNull(email);
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (int i = 0; i < lastShownList.size(); i++) {
            if (email.equals(lastShownList.get(i).getEmail())) {
                return lastShownList.get(i);
            }
        }
        throw new CommandException(MESSAGE_NO_PERSON_IN_LIST);
    }

    /**
     * Finds a job with the given job title in the filtered job list
     * @param title of the job to find a job title matches with the job candidate applied for
     * @param model to retrieve the filtered job list
     */
    public Job findJobMatchingTitle(String title, Model model) throws CommandException {
        requireNonNull(title);
        requireNonNull(model);
        List<Job> lastShownJobList = model.getFilteredJobList();
        Job checkEqualJob = new Job(title, "", 10);

        for (int i = 0; i < lastShownJobList.size(); i++) {
            if (checkEqualJob.isSameJob(lastShownJobList.get(i))) {
                return lastShownJobList.get(i);
            }
        }
        throw new CommandException(MESSAGE_NO_JOB_IN_LIST);
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

        AddApplicationCommand otherAddApplicationCommand = (AddApplicationCommand) other;
        return email.equals(otherAddApplicationCommand.email)
                && jobTitle.equals(otherAddApplicationCommand.jobTitle)
                && status.equals(otherAddApplicationCommand.status);
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
