package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POINTS);

        Name name;
        try {
            name = ParserUtil.parseName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE), pe);
        }

        Points points;
        try {
            points = ParserUtil.parsePoints(argMultimap.getValue(PREFIX_POINTS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE), pe);
        }

        return new AddPointsCommand(name, points);
    }
}
