package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import educonnect.logic.commands.FindCommand;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.Student;
import educonnect.model.student.Tag;
import educonnect.model.student.predicates.EmailContainsKeywordsPredicate;
import educonnect.model.student.predicates.IdContainsKeywordsPredicate;
import educonnect.model.student.predicates.NameContainsKeywordsPredicate;
import educonnect.model.student.predicates.TagContainsKeywordsPredicate;
import educonnect.model.student.predicates.TelegramContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID,
                PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Set<Predicate<Student>> predicates = new HashSet<>();
        argMultimap.getValue(PREFIX_NAME).ifPresent(keywordName ->
                predicates.add(new NameContainsKeywordsPredicate(keywordName))
        );
        argMultimap.getValue(PREFIX_STUDENT_ID).ifPresent(keywordId ->
                predicates.add(new IdContainsKeywordsPredicate(keywordId))
        );
        argMultimap.getValue(PREFIX_EMAIL).ifPresent(keywordName ->
                predicates.add(new EmailContainsKeywordsPredicate(keywordName))
        );
        argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).ifPresent(keywordName ->
                predicates.add(new TelegramContainsKeywordsPredicate(keywordName))
        );
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        for (Tag keywordTag: tagList) {
            predicates.add(new TagContainsKeywordsPredicate(keywordTag));
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
