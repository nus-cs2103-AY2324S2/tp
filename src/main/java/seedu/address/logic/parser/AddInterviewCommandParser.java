package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWER;

import seedu.address.logic.commands.AddInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new AddInterviewCommand object
 */
public class AddInterviewCommandParser implements Parser<AddInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewCommand
     * and returns an AddInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_APPLICANT, PREFIX_INTERVIEWER);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_APPLICANT, PREFIX_INTERVIEWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        LocalDate date = parseDate(argMultimap.getValue(PREFIX_DATE).get());
        LocalTime startTime = parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        Phone applicantPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_APPLICANT).get());
        Phone interviewerPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_INTERVIEWER).get());

        return new AddInterviewCommand(description, applicantPhone, interviewerPhone, date, startTime, endTime);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static LocalDate parseDate(String date) throws ParseException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date format!");
        }
    }

    private static LocalTime parseTime(String time) throws ParseException {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid time format!");
        }
    }
}

