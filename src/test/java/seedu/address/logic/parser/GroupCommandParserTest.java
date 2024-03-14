package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.person.NusId;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.GroupPersonDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

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