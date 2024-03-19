package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AssignCommand} object
 */
public class AssignCommandParser implements Parser<AssignCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AssignCommand}
     * and returns a {@code AssignCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TO);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TO);
        Index taskIndex;
        Index personIndex;
        try {
            taskIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TO).orElse(""));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignCommand.MESSAGE_USAGE), ive);
        }

        return new AssignCommand(taskIndex, personIndex);
    }
}
