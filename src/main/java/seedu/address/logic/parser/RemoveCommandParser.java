package seedu.address.logic.parser;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class RemoveCommandParser {

    public RemoveCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        String[] identifiers = trimmedArgs.split("\\s+");

        if (identifiers.length == 1 && isInteger(identifiers[0])) {
            return new RemoveCommand(ParserUtil.parseIndex(identifiers[0]));
        } else {
            return new RemoveCommand(new NameContainsKeywordsPredicate(Arrays.asList(identifiers)));
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
