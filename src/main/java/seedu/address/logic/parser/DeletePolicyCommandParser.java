package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeletePolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeletePolicyCommandParser implements  Parser<DeletePolicyCommand> {
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
