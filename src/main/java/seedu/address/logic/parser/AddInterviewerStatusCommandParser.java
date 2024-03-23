package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddInterviewerStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new {@code AddInterviewerStatusCommand} object
 */
public class AddInterviewerStatusCommandParser implements Parser<AddInterviewerStatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddApplicantStatusCommand}
     * and returns a {@code AddApplicantStatusCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewerStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Phone phone;
        try {
            phone = ParserUtil.parsePhone(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInterviewerStatusCommand.MESSAGE_USAGE), ive);
        }

        String status = argMultimap.getValue(PREFIX_STATUS).orElse("");

        return new AddInterviewerStatusCommand(phone, new InterviewerStatus(status));
    }
}
