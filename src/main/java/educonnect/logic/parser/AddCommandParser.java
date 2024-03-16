package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_FRIDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_MONDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_SATURDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_SUNDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_THURSDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_TUESDAY;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE_WEDNESDAY;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import educonnect.logic.commands.AddCommand;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Timetable;
import educonnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                         PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_TIMETABLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID,
                PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE, PREFIX_TIMETABLE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TelegramHandle telegramHandle = ParserUtil.parseTelegramHandle(
                    argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Timetable timetable = // ToDo: add support for timetable in student
                ParserUtil.parseTimetable(tokenizeForTimetable(argMultimap.getValue(PREFIX_TIMETABLE).orElse("")));

        Student student = new Student(name, studentId, email, telegramHandle, tagList, timetable);

        return new AddCommand(student);
    }

    /**
     * Tokenizes the input arguments for Timetable under Add Command.
     *
     * @param fullTimetableString a full {@code String} containing the arguments. E.g. "mon: 1-4, 12-14 tue: 14-16 ..."
     * @return an {@code ArrayList<String>}, with each entry containing arguments for each day of the Timetable week.
     */
    public static ArrayList<String> tokenizeForTimetable(String fullTimetableString) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(fullTimetableString, PREFIX_TIMETABLE_MONDAY, PREFIX_TIMETABLE_TUESDAY,
                        PREFIX_TIMETABLE_WEDNESDAY, PREFIX_TIMETABLE_THURSDAY, PREFIX_TIMETABLE_FRIDAY,
                        PREFIX_TIMETABLE_SATURDAY, PREFIX_TIMETABLE_SUNDAY);

        ArrayList<String> allDays = new ArrayList<>();
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_MONDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_TUESDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_WEDNESDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_THURSDAY).orElse(""));
        allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_FRIDAY).orElse(""));

        if (Timetable.is7Days()) {
            allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_SATURDAY).orElse(""));
            allDays.add(argMultimap.getValue(PREFIX_TIMETABLE_SUNDAY).orElse(""));
        }

        return allDays;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
