package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GradeSubjectFilterPredicate;
import seedu.address.model.person.Subject;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GRADE, PREFIX_SUBJECT);
        if (!areAnyPrefixesPresent(argMultimap, PREFIX_GRADE, PREFIX_SUBJECT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GRADE, PREFIX_SUBJECT);

        Grade grade = new Grade();
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        }
        Subject subject = new Subject();
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        }

        return new FilterCommand(new GradeSubjectFilterPredicate(grade, subject));
    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
