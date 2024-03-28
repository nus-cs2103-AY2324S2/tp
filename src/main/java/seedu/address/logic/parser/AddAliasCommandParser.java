package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPLACED;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAliasCommand object.
 */
public class AddAliasCommandParser implements Parser<AddAliasCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_REPLACED);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));
        }

        return new AddAliasCommand(argMultimap.getValue(PREFIX_ALIAS).get(),
                argMultimap.getValue(PREFIX_REPLACED).get());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
