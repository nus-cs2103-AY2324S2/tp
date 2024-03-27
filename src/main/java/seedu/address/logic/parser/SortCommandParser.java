package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Objects;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    public static final String MESSAGE_INVALID_COMMAND_LENGTH = "Invalid command length for sort command"
            + "sort command only accepts 1 arguments";
    public static final String MESSAGE_INVALID_COMMAND_KEYWORD = "Invalid command keyword for sort command";
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        Integer index = null;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] inputString = trimmedArgs.split("\\s+");

        if (inputString.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_LENGTH, SortCommand.MESSAGE_USAGE));
        }

        String keyword = inputString[0];
        if (Objects.equals(keyword, PREFIX_PRIORITY.getPrefix())) {
            index = 0;
        } else if (Objects.equals(keyword, PREFIX_COMPANY_NAME.getPrefix())) {
            index = 1;
        } else if (Objects.equals(keyword, PREFIX_NAME.getPrefix())) {
            index = 2;
        } else if (Objects.equals(keyword, PREFIX_INTERVIEWTIME.getPrefix())) {
            index = 3;
        } else if (Objects.equals(keyword, PREFIX_SALARY.getPrefix())) {
            index = 4;
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_KEYWORD, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(index);
    }
}
