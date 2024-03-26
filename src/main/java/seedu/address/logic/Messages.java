package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_EMPTY_PERSON_LIST = "No person currently displayed";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_EXAM_DISPLAYED_INDEX = "The exam index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_NO_EXAM_SELECTED = "No exam selected. Please select an exam first.";

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
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Matriculation Number: ")
                .append(person.getMatric())
                .append("; Reflection: ")
                .append(person.getReflection())
                .append("; Studio: ")
                .append(person.getStudio())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Scores: ");
        person.getScores().forEach((exam, score) -> builder.append(exam).append(": ").append(score).append(", "));
        return builder.toString();
    }

    /**
     * Formats the {@code exam} for display to the user.
     * @param exam
     * @return
     */

    public static String format(Exam exam) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Exam Name: ")
                .append(exam.getName())
                .append("; Maximum Score: ")
                .append(exam.getMaxScore());
        return builder.toString();
    }
}
