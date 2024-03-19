package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccessGroup;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupCommand;

import seedu.address.model.person.NusId;
import seedu.address.testutil.GroupPersonDescriptorBuilder;



class GroupCommandParserTest {

    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        //Index targetIndex = INDEX_SECOND_PERSON;
        NusId nusid = new NusId(VALID_NUSID_AMY);
        String userInput = " id/" + nusid + GROUP_DESC_HUSBAND
                + TAG_DESC_AMY;

        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder()
                .withNusId(VALID_NUSID_AMY)
                .withGroups(VALID_GROUP_HUSBAND)
                .withTag(VALID_TAG_AMY)
                .build();

        GroupCommand expectedCommand = new GroupCommand(nusid, descriptor);
        //System.out.println(userInput);


        assertParseSuccessGroup(parser, userInput, expectedCommand);
    }


}
