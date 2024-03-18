package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListOrderCommandParser implements Parser<ListOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListOrderCommand
     * and returns a ListOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
