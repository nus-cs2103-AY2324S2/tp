package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteArticleCommand object
 */
public class DeleteArticleCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteArticleCommand
     * and returns a DeleteArticleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteArticleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteArticleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteArticleCommand.MESSAGE_USAGE), pe);
        }
    }
}
