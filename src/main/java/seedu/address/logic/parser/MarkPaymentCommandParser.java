package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkPaymentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for marking payment commands.
 */
public class MarkPaymentCommandParser {
    /**
     * Parses arguments for a payment.
     *
     * @param args Arguments for command.
     * @return A MarkPaymentCommand for execution.
     * @throws ParseException If arguments are unable to be parsed.
     */
    public MarkPaymentCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkPaymentCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkPaymentCommand.MESSAGE_USAGE), ive);
        }
    }
}
