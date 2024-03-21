package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM_TERM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BENEFIT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {
    public AddPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_POLICY_NAME, PREFIX_POLICY_NUMBER, PREFIX_PREMIUM_TERM, PREFIX_PREMIUM, PREFIX_BENEFIT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPolicyCommand.MESSAGE_USAGE), ive);
        }

        String policyName = argMultimap.getValue(PREFIX_POLICY_NAME).orElse("");
        String policyNumber = argMultimap.getValue(PREFIX_POLICY_NUMBER).orElse("");
        String premiumTerm = argMultimap.getValue(PREFIX_PREMIUM_TERM).orElse("");
        String premium = argMultimap.getValue(PREFIX_PREMIUM).orElse("");
        String benefit = argMultimap.getValue(PREFIX_BENEFIT).orElse("");

        return new AddPolicyCommand(index, policyName, policyNumber, premiumTerm, premium, benefit);
    }
}
