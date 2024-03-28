package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;

public class AddAliasCommandParseTest {
    private AddAliasCommandParser parser = new AddAliasCommandParser();

    @Test
    public void parse_missingRequiredFields_failure() {
        String userInputMissingRepacement = "alias -al test";
        assertParseFailure(parser, userInputMissingRepacement,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));

        String userInputMissingAlias = "alias -r result";
        assertParseFailure(parser, userInputMissingAlias,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));

        String multipleAliases = "alias -al test1 -al test -r result";
        assertParseFailure(parser, multipleAliases,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));
    }
}
