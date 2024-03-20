package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMemshipCommand;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Name;
public class AddMemshipCommandParserTest {

    private AddMemshipCommandParser parser = new AddMemshipCommandParser();


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Missing name
        assertParseFailure(parser, " " + PREFIX_MEMSHIP + "T1",
                Name.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE);

        // Missing membership tier
        assertParseFailure(parser, "Alice ",
                Membership.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE);

        // Missing all fields
        assertParseFailure(parser, "",
                Name.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = Membership.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE;
        // Invalid membership tier
        String parserArgs2 = " " + "Alice " + PREFIX_MEMSHIP + "X1";
        assertParseFailure(parser, parserArgs2, expectedMessage);
        // Membership.MESSAGE_CONSTRAINTS contains the message for invalid membership tiers
    }

    @Test
    public void parse_invalidName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE;

        // Name contains non-alphanumeric characters
        assertParseFailure(parser, " " + PREFIX_NAME + "1-lice " + PREFIX_MEMSHIP + "T1", expectedMessage);
        // Name is blank
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_MEMSHIP + "T1", expectedMessage);
    }

    @Test
    public void parse_invalidMembership_failure() {
        // Membership is invalid
        String expectedMessage = Membership.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " " + "Alice " + PREFIX_MEMSHIP + "X1", expectedMessage);
    }

    @Test
    public void parse_missingFields_failure() {
        // Missing name
        String expectedMessage = Name.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE;
        assertParseFailure(parser, PREFIX_MEMSHIP + "T1", expectedMessage);
        // Missing membership
        expectedMessage = Membership.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "Alice", expectedMessage);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedName = new Name("Alice");
        String expectedMembership = "T1";
        // Correct command
        assertParseSuccess(parser, " " + "Alice " + PREFIX_MEMSHIP + "T1",
                new AddMemshipCommand(expectedName, expectedMembership));
    }
}
