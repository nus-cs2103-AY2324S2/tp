package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Adds an Interview to the talent tracker.
 */
public class AddInterviewCommand extends Command{

    public static final String COMMAND_WORD = AddPersonCommand.COMMAND_WORD + "_interview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the talent tracker. "
            + AddPersonCommand.MESSAGE_USAGE;


    public static final String MESSAGE_SUCCESS = "New Interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This Interview already exists in the talent tracker";

    private String description;
    private Phone applicant;
    private Phone interviewer;

    // Format YYYY-MM-DD
    private LocalDate date;
    // Format HH:mm
    private LocalTime startTime;
    private LocalTime endTime;


    /**
     * Creates an AddInterviewCommand to add the specified {@code Person}
     */
    public AddInterviewCommand(String description, Phone applicant, Phone interviewer, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.applicant = applicant;
        this.interviewer = interviewer;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        boolean isFoundApplicant = false;
        boolean isFoundInterviewer = false;
        Phone targetApplicantPhone = applicant;
        Phone targetInterviewerPhone = interviewer;
        Person applicantSearch = null;
        Person interviewerSearch = null;

        for (Person p : lastShownList) {
            if (p.getPhone().equals(targetApplicantPhone)) {
                applicantSearch = p;
                isFoundApplicant = true;
                break;
            }
        }
        for (Person p : lastShownList) {
            if (p.getPhone().equals(targetInterviewerPhone)) {
                interviewerSearch = p;
                isFoundInterviewer = true;
                break;
            }
        }


        if (!isFoundApplicant || !isFoundInterviewer) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_IN_LIST);
        }
//        else if (!(applicantSearch instanceof Applicant) || !(interviewerSearch instanceof Interviewer)) {
//            throw new CommandException("Phone Number error");
//        }


        Interview interview = new Interview(applicantSearch, interviewerSearch, date, startTime, endTime, description);

        if (model.hasInterview(interview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        model.addInterview(interview);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatInterview(interview)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterviewCommand)) {
            return false;
        }

        AddInterviewCommand otherCommmand = (AddInterviewCommand) other;
//        return interview.equals(otherCommmand.interview);
        return false;
    }

    @Override
    public String toString() {
        return "Description: " + description + " applicant: " + applicant.toString() + " interviewer: "
                + interviewer.toString() + " date: " + date.toString() + " start: " + startTime.toString() + " end: "
                + endTime.toString();
    }
}
