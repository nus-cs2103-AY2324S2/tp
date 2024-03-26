package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ClientStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ClientStatusCommand} object
 */
public class ClientStatusCommandParser implements Parser<ClientStatusCommand> {
    @Override
    public ClientStatusCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientStatusCommand.MESSAGE_USAGE), ive);
        }

        String direction = argMultimap.getValue(PREFIX_STATUS).orElse("");

        return new ClientStatusCommand(index, direction);
    }
}
