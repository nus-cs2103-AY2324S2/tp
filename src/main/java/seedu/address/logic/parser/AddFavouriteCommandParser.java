package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDICES;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddFavouriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddFavouriteCommand object
 */
public class AddFavouriteCommandParser implements Parser<AddFavouriteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFavouriteCommand
     * and returns an AddFavouriteCommand object for execution
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddFavouriteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDICES);

        // Ensure no preamble exists.
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavouriteCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNonEmptyKeywordValues(PREFIX_INDICES);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INDICES);
        List<String> listIndices = argMultimap.getAllValues(PREFIX_INDICES);
        if (listIndices.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavouriteCommand.MESSAGE_USAGE));
        }
        String[] indexKeywordsArray = listIndices.get(0).split(",");
        List<Index> indices = new ArrayList<>();
        for (String index : indexKeywordsArray) {
            try {
                Index currentIndex = ParserUtil.parseIndex(index);
                indices.add(currentIndex);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddFavouriteCommand.MESSAGE_USAGE), pe);
            }
        }
        return new AddFavouriteCommand(new HashSet<>(indices));
    }
}
