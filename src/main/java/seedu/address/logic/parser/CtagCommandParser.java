package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CtagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new CtagCommand object
 */
public class CtagCommandParser implements Parser<CtagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CtagCommand
     * and returns a CtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CtagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String tagName = args.trim();
        if (tagName.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CtagCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(tagName);

        return new CtagCommand(tag);
    }
}
