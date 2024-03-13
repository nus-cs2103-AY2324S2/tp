package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAR;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Star;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class StarCommandParser implements Parser<StarCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StarCommand
     * and returns an StarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StarCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAR); // tokenise args by PREFIX_STAR


        if (!arePrefixesPresent(argMultimap, PREFIX_STAR) ||
                !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
        } // if no PREFIX used, or if the Preamble is empty, we throw a ParseException

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE), pe);
        }

        Star star = new Star(0);

        return new StarCommand(index, star);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
