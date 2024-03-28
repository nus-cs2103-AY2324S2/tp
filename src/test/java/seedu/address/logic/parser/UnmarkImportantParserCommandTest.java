package seedu.address.logic.parser;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkImportantCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.testutil.UnmarkImportantDescriptorBuilder;

public class UnmarkImportantParserCommandTest {
    private UnmarkImportantCommandParser parser = new UnmarkImportantCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkImportantCommand() {
        Name groupName1 = new Name("group 1");
        UnmarkImportantCommand.UnmarkImportantDescriptor descriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP).build();
        UnmarkImportantCommand expectedCommand = new UnmarkImportantCommand(groupName1, descriptor);

        assertParseSuccess(parser, "group 1 -s C#", expectedCommand);

        Name groupName2 = new Name("group 1");
        UnmarkImportantCommand.UnmarkImportantDescriptor descriptor2 = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA, VALID_SKILL_CPP).build();
        assertParseSuccess(parser, "group 1 -s C# -s Java -s C++", new UnmarkImportantCommand(groupName2, descriptor2));
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        assertParseFailure(parser, "group 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkImportantCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "group 1 -s",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkImportantCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "gr@up 1 -s Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkImportantCommand.MESSAGE_USAGE));
    }
}
