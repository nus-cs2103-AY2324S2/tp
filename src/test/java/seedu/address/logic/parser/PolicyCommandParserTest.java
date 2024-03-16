package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.model.person.Policy;

public class PolicyCommandParserTest {
    private PolicyCommandParser parser = new PolicyCommandParser();
    private final String nonEmptyPolicy = "Some policy.";

    @Test
    public void parse_indexSpecified_success() {
        // have policy
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY + nonEmptyPolicy;
        PolicyCommand expectedCommand = new PolicyCommand(INDEX_FIRST_PERSON, new Policy(nonEmptyPolicy));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no policy
        userInput = targetIndex.getOneBased() + " " + PREFIX_POLICY;
        expectedCommand = new PolicyCommand(INDEX_FIRST_PERSON, new Policy(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, PolicyCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, PolicyCommand.COMMAND_WORD + " " + nonEmptyPolicy, expectedMessage);
    }
}