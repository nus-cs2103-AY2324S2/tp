package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput,
            Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code firstArgs} and {@code secondArgs} by {@code parser}
     * is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertMeetingParseSuccess(MeetingCommandParser<? extends Command> parser,
                                                 String firstArgs, String secondArgs, Command expectedCommand) {
        try {
            Command command = parser.parse(firstArgs, secondArgs);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code firstArgs} and {@code secondArgs} by {@code parser}
     * is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertMeetingParseFailure(MeetingCommandParser<? extends Command> parser,
                                                 String firstArgs, String secondArgs, String expectedMessage) {
        try {
            parser.parse(firstArgs, secondArgs);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
