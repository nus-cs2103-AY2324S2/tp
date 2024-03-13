package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDICES;

import seedu.address.logic.commands.AddFavouriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddFavouriteCommandParser implements Parser<AddFavouriteCommand> {
    public AddFavouriteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDICES);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDICES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddFavouriteCommand.MESSAGE_USAGE));
        }
        // Ensure no preamble exists.
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavouriteCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNonEmptyKeywordValues(PREFIX_INDICES);
        return new AddFavouriteCommand(indices);
    }
}
