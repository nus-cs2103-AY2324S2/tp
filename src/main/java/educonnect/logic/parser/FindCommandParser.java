package educonnect.logic.parser;

import educonnect.logic.commands.AddCommand;
import educonnect.logic.commands.FindCommand;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.*;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;
import educonnect.model.student.predicates.*;

import java.util.HashSet;
import java.util.Set;
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
