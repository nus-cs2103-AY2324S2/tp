package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Policy;

public class PolicyCommandParserTest {
    private PolicyCommandParser parser = new PolicyCommandParser();
    private final String sample = "some policy";
    private final Policy samplePolicy = new Policy(sample);
    private final Set<Policy> nonEmptyPolicy = new HashSet<>();
    private final Set<Policy> emptyPolicy = new HashSet<>();

    @Test
    public void parse_indexSpecified_success() {
        nonEmptyPolicy.add(samplePolicy);
        // have policy
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY + sample;
        PolicyCommand expectedCommand = new PolicyCommand(INDEX_FIRST_PERSON, nonEmptyPolicy);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no policy
        userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY + " ";
        expectedCommand = new PolicyCommand(INDEX_FIRST_PERSON, emptyPolicy);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validInput_parsesCorrectly() throws ParseException {
        String args = "1 po/PolicyName ed/31-12-2024 pm/100.50";
        try {
            PolicyCommand policyCommand = parser.parse(args);
            assertEquals(Index.fromOneBased(1), policyCommand.getIndex());

            Set<Policy> expectedPolicies = new HashSet<>();
            expectedPolicies.add(new Policy("PolicyName", LocalDate.of(2024, 12, 31),
                    100.50));
            assertEquals(expectedPolicies, policyCommand.getPolicies());
        } catch (ParseException e) {
            throw new ParseException(e.getMessage());
        }

        String args2 = "1 po/PolicyName pm/100.50";
        try {
            PolicyCommand policyCommand = parser.parse(args2);
            assertEquals(Index.fromOneBased(1), policyCommand.getIndex());

            Set<Policy> expectedPolicies = new HashSet<>();
            expectedPolicies.add(new Policy("PolicyName", null, 100.50));
            assertEquals(expectedPolicies, policyCommand.getPolicies());
        } catch (ParseException e) {
            throw new ParseException(e.getMessage());
        }

        String args3 = "1 po/PolicyName ed/31-12-2024";
        try {
            PolicyCommand policyCommand = parser.parse(args3);
            assertEquals(Index.fromOneBased(1), policyCommand.getIndex());

            Set<Policy> expectedPolicies = new HashSet<>();
            expectedPolicies.add(new Policy("PolicyName", LocalDate.of(2024, 12, 31),
                    0.0));
            assertEquals(expectedPolicies, policyCommand.getPolicies());
        } catch (ParseException e) {
            throw new ParseException(e.getMessage());
        }
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyCommand.MESSAGE_USAGE);

        // no policy prefix
        assertParseFailure(parser, " 1", expectedMessage);

        // no parameters
        assertParseFailure(parser, " ", expectedMessage);

        // no index
        assertParseFailure(parser, " po/Policy 1", expectedMessage);
    }
}
