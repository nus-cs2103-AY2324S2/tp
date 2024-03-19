package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */

public class ParserTaskUtil {

    public static final String MESSAGE_INVALID_DEADLINE = "Invalid date/time format. Please use dd-MM-yyyy HHmm";

    /**
     * Parses a {@code String taskName} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseTaskName(String taskName) {
        requireNonNull(taskName);
        String trimmedTaskName = taskName.trim();
        return trimmedTaskName;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed before parsing.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        String trimmedDeadline = deadline.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(trimmedDeadline, formatter);
            return new Deadline(parsedDeadline);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DEADLINE);
        }
    }
}
