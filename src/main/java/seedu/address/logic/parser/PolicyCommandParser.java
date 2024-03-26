package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POLICY, PREFIX_EXPIRY_DATE,
                PREFIX_PREMIUM);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyCommand.MESSAGE_USAGE));
        }

        Set<Policy> policies = new HashSet<>();
        List<String> policyValues = argMultimap.getAllValues(PREFIX_POLICY);
        List<String> expiryDates = argMultimap.getAllValues(PREFIX_EXPIRY_DATE);
        List<String> premiums = argMultimap.getAllValues(PREFIX_PREMIUM);

        for (int i = 0; i < policyValues.size(); i++) {
            String policy = policyValues.get(i);
            String expiryDateStr = expiryDates.size() > i ? expiryDates.get(i) : null;
            String premiumStr = premiums.size() > i ? premiums.get(i) : null;

            if (policy.isEmpty()) {
                return new PolicyCommand(index, policies);
            }

            policy = policy.trim();
            LocalDate expiryDate = null;
            double premium = 0.0;

            if (expiryDateStr != null && !expiryDateStr.isEmpty()) {
                expiryDate = ParserUtil.parseExpiryDate(expiryDateStr);
            }

            if (premiumStr != null && !premiumStr.isEmpty()) {
                premium = ParserUtil.parsePremium(premiumStr);
            }

            Policy newPolicy = new Policy(policy, expiryDate, premium);
            policies.add(newPolicy);
        }

        return new PolicyCommand(index, policies);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
