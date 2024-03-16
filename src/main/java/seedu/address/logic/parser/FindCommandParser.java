package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdAndNameContainsQueryIdAndNamePredicate;
import seedu.address.model.person.IdContainsQueryIdPredicate;
import seedu.address.model.person.NameContainsQueryNamePredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID);

        if (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String queryId = argMultimap.getValue(PREFIX_ID).get().trim();
            String queryName = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (queryId.isEmpty() || queryName.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new IdAndNameContainsQueryIdAndNamePredicate(queryId, queryName));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {

            String queryId = argMultimap.getValue(PREFIX_ID).get().trim();
            if (queryId.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new IdContainsQueryIdPredicate(queryId));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String queryName = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (queryName.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new NameContainsQueryNamePredicate(queryName));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
