package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddMemshipCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Membership;

public class AddMemshipCommandParserTest {

    private AddMemshipCommandParser parser = new AddMemshipCommandParser();


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Missing name
        assertParseFailure(parser, " " + PREFIX_MEMSHIP + "T1",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMemshipCommand.MESSAGE_USAGE));

        // Missing membership tier
        assertParseFailure(parser, PREFIX_NAME + " Alice ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMemshipCommand.MESSAGE_USAGE));

        // Missing all fields
        assertParseFailure(parser, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMemshipCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid name
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_MEMSHIP + "T1",
                Name.MESSAGE_CONSTRAINTS); // Name.MESSAGE_CONSTRAINTS contains the message for invalid names

        // Invalid membership tier
        assertParseFailure(parser, " " + PREFIX_NAME + " Alice " + PREFIX_MEMSHIP + "X1",
                Membership.MESSAGE_CONSTRAINTS);
        // Membership.MESSAGE_CONSTRAINTS contains the message for invalid membership tiers
    }

    @Test
    public void parse_invalidName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS; // Assuming this contains the validation message for names
        // Name contains non-alphanumeric characters
        assertParseFailure(parser, " " + PREFIX_NAME + "1-lice " + PREFIX_MEMSHIP + "T1", expectedMessage);
        // Name is blank
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_MEMSHIP + "T1", expectedMessage);
    }

    @Test
    public void parse_invalidMembership_failure() {
        String expectedMessage = Membership.MESSAGE_CONSTRAINTS; // Assuming this contains the validation message for memberships
        // Membership is invalid
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_MEMSHIP + "X1", expectedMessage);
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddMemshipCommand.MESSAGE_USAGE);
        // Missing name
        assertParseFailure(parser, PREFIX_MEMSHIP + "T1", expectedMessage);
        // Missing membership
        assertParseFailure(parser, PREFIX_NAME + "Alice", expectedMessage);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedName = new Name("Alice");
        String expectedMembership = "T1";
        // Correct command
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_MEMSHIP + "T1",
                new AddMemshipCommand(expectedName, expectedMembership));
    }
}
