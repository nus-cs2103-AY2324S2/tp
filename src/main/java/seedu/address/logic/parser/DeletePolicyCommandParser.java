package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeletePolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePolicyCommand object.
 */
public class DeletePolicyCommandParser implements Parser<DeletePolicyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePolicyCommand
     * and returns a DeletePolicyCommand object for execution.
     *
     * @param args The arguments provided by the user.
     * @return A DeletePolicyCommand object representing the user's command.
     * @throws ParseException If the user's input does not conform to the expected format.
     */
    public DeletePolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_POLICY_NAME);

        Index index;
        String policyName = argMultimap.getValue(PREFIX_POLICY_NAME).orElse(null);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            if (policyName == null) {
                throw new IllegalValueException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePolicyCommand.MESSAGE_USAGE), ive);
        }

        return new DeletePolicyCommand(index, policyName);
    }
}
