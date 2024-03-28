package seedu.teachstack.logic.parser;

import static seedu.teachstack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachstack.logic.commands.CommandTestUtil.GROUP_DESC_1;
import static seedu.teachstack.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.teachstack.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static seedu.teachstack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.teachstack.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GROUP_GROUP1;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.teachstack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.teachstack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.teachstack.logic.commands.GroupCommand;
import seedu.teachstack.model.group.Group;
import seedu.teachstack.model.person.StudentId;


public class GroupCommandParserTest {
    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        Set<Group> group = new HashSet<>();
        group.add(new Group(VALID_GROUP_GROUP1));
        Set<StudentId> studentIds = new HashSet<>();
        studentIds.add(new StudentId(VALID_STUDENTID_AMY));
        studentIds.add(new StudentId(VALID_STUDENTID_BOB));
        String stuff = PREAMBLE_WHITESPACE + VALID_GROUP_GROUP1
                + STUDENTID_DESC_AMY + STUDENTID_DESC_BOB;

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUP_DESC_1
                + STUDENTID_DESC_AMY + STUDENTID_DESC_BOB,
                new GroupCommand(group, studentIds));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE);

        //missing group prefix
        assertParseFailure(parser, VALID_GROUP_GROUP1 + STUDENTID_DESC_AMY, expectedMessage);

        // missing studentId prefix
        assertParseFailure(parser, GROUP_DESC_1 + VALID_STUDENTID_AMY, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GROUP_GROUP1 + VALID_STUDENTID_AMY + VALID_STUDENTID_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid group
        assertParseFailure(parser, INVALID_GROUP_DESC + STUDENTID_DESC_BOB, Group.MESSAGE_CONSTRAINTS);

        // invalid studentId
        assertParseFailure(parser, GROUP_DESC_1 + INVALID_STUDENTID_DESC, StudentId.MESSAGE_CONSTRAINTS);
    }
}
