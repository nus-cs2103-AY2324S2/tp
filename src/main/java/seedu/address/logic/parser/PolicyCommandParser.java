package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Policy;

/**
 * Parses input arguments and creates a new {@code PolicyCommand} object
 */
public class PolicyCommandParser implements Parser<PolicyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code PolicyCommand}
     * and returns a {@code PolicyCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POLICY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyCommand.MESSAGE_USAGE), ive);
        }

        Set<Policy> policies = new HashSet<>();
        for (String policy : argMultimap.getAllValues(PREFIX_POLICY)) {
            if (policy.isEmpty()) {
                return new PolicyCommand(index, policies);
            }
            policies.add(new Policy(policy));
        }
        return new PolicyCommand(index, policies);
    }
}
