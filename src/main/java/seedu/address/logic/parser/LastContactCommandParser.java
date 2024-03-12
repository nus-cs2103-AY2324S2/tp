package seedu.address.logic.parser;

import seedu.address.logic.commands.LastContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class LastContactCommandParser implements Parser<LastContactCommand> {

    public LastContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastContactCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmedArgs.split(" dt/ ");
        String name = parts[0];
        String dateTime = parts[1];


        return new LastContactCommand(name, dateTime);
    }

}
