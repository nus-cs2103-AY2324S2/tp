package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import seedu.address.logic.commands.RateMateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.Rating;

/**
 * Parses input arguments and creates a new RateMateCommand object
 */
public class RateMateCommandParser implements Parser<RateMateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RateMateCommand
     * and returns and RateMateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateMateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_RATING);

        QueryableCourseMate queryableCourseMate;

        try {
            queryableCourseMate = ParserUtil.parseQueryableCourseMate(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE), pe);
        }

        argMultiMap.verifyNoDuplicatePrefixesFor(PREFIX_RATING);

        Rating rating = null;
        if (argMultiMap.getValue(PREFIX_RATING).isPresent()) {
            rating = ParserUtil.parseRating(argMultiMap.getValue(PREFIX_RATING).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE));
        }

        return new RateMateCommand(queryableCourseMate, rating);
    }
}
