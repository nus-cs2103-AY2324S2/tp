package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipAddDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Deadline;

/**
 * Parses input arguments and creates a new InternshipAddDeadlineCommand object
 */
public class InternshipAddDeadlineCommandParser implements InternshipParser<InternshipAddDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipAddDeadlineCommand
     * and returns an InternshipAddDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipAddDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SELECT_TASK, PREFIX_DEADLINE);

        Index internshipIndex;

        if (argMultimap.getValue(PREFIX_DEADLINE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddDeadlineCommand.MESSAGE_EMPTY_DEADLINE));
        }

        if (argMultimap.getValue(PREFIX_SELECT_TASK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddDeadlineCommand.MESSAGE_INVALID_TASK_INDEX));
        }

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddDeadlineCommand.MESSAGE_USAGE));
        }

        try {
            internshipIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddDeadlineCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DEADLINE);

        Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SELECT_TASK).get());

        Deadline deadline = InternshipParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        return new InternshipAddDeadlineCommand(internshipIndex, taskIndex, deadline);
    }
}
