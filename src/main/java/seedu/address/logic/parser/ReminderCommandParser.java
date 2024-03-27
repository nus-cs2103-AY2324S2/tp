package seedu.address.logic.parser;

import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of the ReminderCommand
 * and returns a ReminderCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */

public class ReminderCommandParser implements Parser<ReminderCommand> {
    public static final String INVALID_NUMBER_OF_DAYS = "Reminder should be followed by a positive integer for the number of days. \n"
            + "Example: reminder 4";
    public static final String NEGATIVE_NUMBER_OF_DAYS = "Number of days cannot be negative.";

    /**
     * Parses the given {@code String} of arguments in the context of the ReminderCommand
     * and returns a ReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderCommand parse(String args) throws ParseException {
        try {
            int numberOfDays = Integer.parseInt(args.trim());
            if (numberOfDays <= 0) {
                throw new ParseException(NEGATIVE_NUMBER_OF_DAYS);
            }
            return new ReminderCommand(numberOfDays);
        } catch (NumberFormatException e) {
            throw new ParseException(INVALID_NUMBER_OF_DAYS);
        }
    }
}