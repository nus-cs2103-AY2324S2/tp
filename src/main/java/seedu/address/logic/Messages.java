package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSON_NOT_IN_LIST = "The person you are looking for is not on the list";
    public static final String MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER = "The phone number you have keyed is is "
            + "not an applicant phone number.";
    public static final String MESSAGE_INCORRECT_INTERVIEWER_PHONE_NUMBER = "The phone number you have keyed is is "
            + "not an interviewer phone number.";
    public static final String MESSAGE_INCORRECT_INTERVIEWER_AND_APPLICANT_PHONE_NUMBER =
            "The phone number you have keyed is is not an applicant nor an interviewer phone number.";
    public static final String MESSAGE_INCORRECT_STATUS_APPLICANT = "Only applicants can be given this status";
    public static final String MESSAGE_INCORRECT_STATUS_INTERVIEWER = "Only interviewers can be given this status";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_END_TIME = "The end time is before the start time!";
    public static final String MESSAGE_INTERVIEW_NOT_IN_LIST = "The interview you are looking for is not on the list";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    public static final String MESSAGE_NOT_INTEGER = "The provided argument is not an integer";
    public static final String MESSAGE_NOT_DATE = "The provided argument is not a valid date";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code interview} for display to the user.
     */
    public static String formatInterview(Interview interview) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Applicant: ")
                .append(interview.getApplicant().getName())
                .append(" Interviewer: ")
                .append(interview.getInterviewer().getName())
                .append(" Date: ")
                .append(interview.getDate())
                .append(" From: ")
                .append(interview.getStartTime())
                .append(" to: ")
                .append(interview.getEndTime());;
        return builder.toString();
    }

}
