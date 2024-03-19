package educonnect.logic.parser;

import educonnect.logic.commands.AddCommand;
import educonnect.logic.commands.FindCommand;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.Student;
import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.*;

import educonnect.model.student.predicates.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_TAG);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        List<Predicate<Student>> predicates = new ArrayList<>();
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {

        }
        if (arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID)) {
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TELEGRAM_HANDLE)) {
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
        }


        return new FindCommand(predicates);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
