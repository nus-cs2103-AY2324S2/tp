package seedu.internhub.logic.parser;

import seedu.internhub.logic.commands.ReminderCommand;
import seedu.internhub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReminderCommand object
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    public static final String INVALID_NUMBER_OF_DAYS = "Number of days must be a positive integer.";

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns a ReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReminderCommand parse(String args) throws ParseException {
        try {
            int numberOfDays = Integer.parseInt(args.trim());
            if (numberOfDays <= 0) {
                throw new ParseException(INVALID_NUMBER_OF_DAYS);
            }
            return new ReminderCommand(numberOfDays);
        } catch (NumberFormatException e) {
            throw new ParseException(INVALID_NUMBER_OF_DAYS);
        }
    }
}
