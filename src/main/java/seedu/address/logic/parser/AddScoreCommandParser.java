package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Score;

/**
 * Parses input arguments and creates a new AddScoreCommand object
 */
public class AddScoreCommandParser implements Parser<AddScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScoreCommand
     * and returns an AddScoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScoreCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SCORE);

        if (argMultimap.getPreamble().isEmpty() || !argMultimap.getValue(PREFIX_SCORE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SCORE);

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Score score = ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get());

        return new AddScoreCommand(index, score);
    }
}
