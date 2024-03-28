package seedu.address.logic.parser;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkImportantCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.testutil.MarkImportantDescriptorBuilder;

public class MarkImportantParserCommandTest {
    private MarkImportantCommandParser parser = new MarkImportantCommandParser();

    @Test
    public void parse_validArgs_returnsMarkImportantCommand() {
        Name groupName1 = new Name("group 1");
        MarkImportantCommand.MarkImportantDescriptor descriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP).build();
        MarkImportantCommand expectedCommand = new MarkImportantCommand(groupName1, descriptor);

        assertParseSuccess(parser, "group 1 -s C#", expectedCommand);

        Name groupName2 = new Name("group 1");
        MarkImportantCommand.MarkImportantDescriptor descriptor2 = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA, VALID_SKILL_CPP).build();
        assertParseSuccess(parser, "group 1 -s C# -s Java -s C++", new MarkImportantCommand(groupName2, descriptor2));
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        assertParseFailure(parser, "group 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkImportantCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "group 1 -s",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkImportantCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "gr@up 1 -s Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkImportantCommand.MESSAGE_USAGE));
    }
}
