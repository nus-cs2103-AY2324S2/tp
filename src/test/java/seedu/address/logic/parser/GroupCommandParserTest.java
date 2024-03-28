package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccessGroup;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Tag;
import seedu.address.testutil.GroupPersonDescriptorBuilder;



class GroupCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE);
    private GroupCommandParser parser = new GroupCommandParser();
    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "E0123456 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "E0123456 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        //Index targetIndex = INDEX_SECOND_PERSON;
        NusId nusId = new NusId(VALID_NUSID_AMY);
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(nusId);

        String userInput = " id/" + nusId + GROUP_DESC_HUSBAND + TAG_DESC_AMY;
        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder()
                .withGroups(VALID_GROUP_HUSBAND)
                .withTag(VALID_TAG_AMY)
                .build();

        GroupCommand expectedCommand = new GroupCommand(nusIdSet, descriptor);
        assertParseSuccessGroup(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedMultipleValues_success() {
        Set<NusId> nusIdSet = new HashSet<>();
        NusId nusIdAmy = new NusId(VALID_NUSID_AMY);
        NusId nusIdBob = new NusId(VALID_NUSID_BOB);
        nusIdSet.add(nusIdAmy);
        nusIdSet.add(nusIdBob);

        String userInput = " id/" + nusIdAmy + " id/" + nusIdBob + GROUP_DESC_HUSBAND + TAG_DESC_AMY;
        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder()
                .withGroups(VALID_GROUP_HUSBAND)
                .withTag(VALID_TAG_AMY)
                .build();

        GroupCommand expectedCommand = new GroupCommand(nusIdSet, descriptor);
        assertParseSuccessGroup(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        String defaultNusId = "E1234567";
        NusId nusId = new NusId((defaultNusId));
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(nusId);

        // tag
        String userInput = " id/" + defaultNusId + TAG_DESC_AMY;
        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder()
                .withTag(VALID_TAG_AMY).build();
        GroupCommand expectedCommand = new GroupCommand(nusIdSet, descriptor);
        assertParseSuccessGroup(parser, userInput, expectedCommand);

        // group
        userInput = " id/" + defaultNusId + GROUP_DESC_HUSBAND;
        descriptor = new GroupPersonDescriptorBuilder()
                .withGroups(VALID_GROUP_HUSBAND).build();
        expectedCommand = new GroupCommand(nusIdSet, descriptor);
        assertParseSuccessGroup(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " id/E0123456" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, " id/E0123456" + INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS); // invalid group
    }
}
