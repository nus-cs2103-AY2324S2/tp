package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

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

        if (argMultimap.getValue(PREFIX_TASK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddTaskCommand.MESSAGE_EMPTY_TASK));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipAddTaskCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK);

        Task task = InternshipParserUtil.parseTask(argMultimap.getValue(PREFIX_TASK).get());

        return new InternshipAddTaskCommand(index, task);
    }
}
