package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddPointsCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Points;

public class AddPointsCommandParserTest {
    private AddPointsCommandParser parser = new AddPointsCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // Successful scenario where both name and points are provided
        Name expectedName = new Name("Alice");
        Points expectedPoints = new Points("50");
        AddPointsCommand expectedCommand = new AddPointsCommand(expectedName, expectedPoints);
        assertParseSuccess(parser, PREFIX_NAME + "Alice " + PREFIX_POINTS + "50", expectedCommand);

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Missing name prefix
        assertParseFailure(parser, " Alice " + PREFIX_POINTS + " 50",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE));

        // Missing points prefix
        assertParseFailure(parser, PREFIX_NAME + " Alice " + " 50",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE));

        // Missing all fields
        assertParseFailure(parser, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddPointsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid name
        assertParseFailure(parser, PREFIX_NAME + " 1-lice " + PREFIX_POINTS + " 50",
                Name.MESSAGE_CONSTRAINTS); // Name.MESSAGE_CONSTRAINTS contains the message for invalid names

        // Invalid points
        assertParseFailure(parser, PREFIX_NAME + " Alice " + PREFIX_POINTS + " -50",
                Points.MESSAGE_CONSTRAINTS); // Points.MESSAGE_CONSTRAINTS contains the message for invalid points
    }
}
