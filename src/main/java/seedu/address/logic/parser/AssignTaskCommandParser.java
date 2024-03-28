package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AssignTaskCommand object
 */
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {

    @Override
    public AssignTaskCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TASK, PREFIX_DEADLINE, PREFIX_TO);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK, PREFIX_DEADLINE, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
        }

        String taskName = ParserTaskUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK).get());
        Deadline deadline = ParserTaskUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Index personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TO).get());

        Task task = new Task(taskName, deadline);
        return new AssignTaskCommand(task, personIndex);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
