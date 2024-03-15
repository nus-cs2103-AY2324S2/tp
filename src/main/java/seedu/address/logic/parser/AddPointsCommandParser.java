package seedu.address.logic.parser;

import java.util.Optional;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;

import seedu.address.logic.commands.AddPointsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Points;

/**
 * Parses input arguments and creates a new {@code AddPointsCommand} object
 */
public class AddPointsCommandParser implements Parser<AddPointsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddPointsCommand}
     * and returns an {@code AddPointsCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPointsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POINTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_POINTS) ||
                !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElse(""));
        Points points = ParserUtil.parsePoints(argMultimap.getValue(PREFIX_POINTS).orElse(""));

        return new AddPointsCommand(name, points);
    }

    /**
     * Returns true if all the given prefixes are present in the given ArgumentMultimap and not empty.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            Optional<String> value = argumentMultimap.getValue(prefix);
            if (!value.isPresent() || value.get().isEmpty()) {  // Modified line
                return false;
            }
        }
        return true;
    }

}