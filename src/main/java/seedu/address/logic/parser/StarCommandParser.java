package seedu.address.logic.parser;

import seedu.address.logic.commands.StarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class StarCommandParser implements Parser<StarCommand> {

    /**
     * Parses the given {@code userInput} and returns a {@code StarCommand} object.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public StarCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
        }

        String contactName = argMultimap.getValue(PREFIX_NAME).orElseThrow();

        return new StarCommand(contactName);
    }
}
