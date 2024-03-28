package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a MeetingCommandParser that is able to parse user input into a {@code MeetingCommand}
 * of type {@code MeetingCommand}.
 */
public interface MeetingCommandParser<T extends Command> {
    /**
     * Parses {@code firstArgument} into a command and returns it.
     * Parses {@code secondArgument} into a command and returns it.
     *
     * @throws ParseException if {@code firstArgument} or {@code secondArgument}
     *                        does not conform the expected format
     */
    T parse(String firstArgument, String secondArgument) throws ParseException;
}
