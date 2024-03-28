package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.FilterPersonsByStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Status;


/**
 * Parses input arguments and creates a new FilterPersonsByStatusCommandParser object
 */
public class FilterPersonsByStatusCommandParser implements Parser<FilterPersonsByStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of FilterPersonsByStatusCommand
     * and returns a FilterPersonsByStatusCommand object for execution.
     * @throws ParseException if the given status is invalid
     */
    public FilterPersonsByStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Status status;
        try {
            String statusArgument = argMultimap.getPreamble().trim();
            if (ApplicantStatus.isValidStatus(statusArgument)) {
                status = new ApplicantStatus(statusArgument);
            } else if (InterviewerStatus.isValidStatus(statusArgument)) {
                status = new InterviewerStatus(statusArgument);
            } else {
                throw new ParseException(Status.MESSAGE_USAGE);
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Status.MESSAGE_USAGE), ive);
        }

        return new FilterPersonsByStatusCommand(status);
    }
}

