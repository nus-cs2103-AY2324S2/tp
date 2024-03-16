package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HoursCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WorkHours;

/**
 * Parses input arguments and creates a new HoursCommand object
 */
public class HoursCommandParser implements Parser<HoursCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HoursCommand
     * and returns a HoursCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HoursCommand parse(String args) throws ParseException {
        String[] tokens = args.trim().split("\\s+");
        if (tokens.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HoursCommand.MESSAGE_USAGE));
        }
        Phone phoneNumber = ParserUtil.parsePhone(tokens[0]);
        WorkHours hoursWorked;
        hoursWorked = ParserUtil.parseWorkHours(tokens[1]);
        return new HoursCommand(phoneNumber, hoursWorked);
    }
}
