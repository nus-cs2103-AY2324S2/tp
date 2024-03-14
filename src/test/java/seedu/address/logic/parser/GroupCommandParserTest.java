package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.person.NusId;
import seedu.address.testutil.GroupPersonDescriptorBuilder;



class GroupCommandParserTest {

    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        //Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = VALID_NUSID_AMY + GROUP_DESC_HUSBAND
                + TAG_DESC_AMY;

        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder().withNusId(VALID_NUSID_AMY)
                .withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND)
                .withTag(VALID_TAG_AMY)
                .build();
        GroupCommand expectedCommand = new GroupCommand(new NusId(VALID_NUSID_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
