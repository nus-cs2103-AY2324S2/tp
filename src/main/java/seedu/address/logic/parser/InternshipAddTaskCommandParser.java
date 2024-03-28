package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.InternshipParserUtil.MESSAGE_INVALID_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipAddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Task;

/**
 * Parses input arguments and creates a new InternshipAddTaskCommand object
 */
public class InternshipAddTaskCommandParser implements InternshipParser<InternshipAddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipAddTaskCommand
     * and returns an InternshipAddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipAddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK);

        Index index;
        Task task;

        if (argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_TASK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddTaskCommand.MESSAGE_USAGE));
        }

        try {
            index = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INDEX, pe);
        }

        try {
            task = InternshipParserUtil.parseTask(argMultimap.getValue(PREFIX_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddTaskCommand.MESSAGE_EMPTY_TASK), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK);

        return new InternshipAddTaskCommand(index, task);
    }
}
