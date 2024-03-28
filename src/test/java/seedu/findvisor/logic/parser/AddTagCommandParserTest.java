package seedu.findvisor.logic.parser;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_TAG_DESC_EMPTY;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_TAG_DESC_SPACE;
import static seedu.findvisor.logic.commands.CommandTestUtil.SET_OF_VALID_TAGS;
import static seedu.findvisor.logic.commands.CommandTestUtil.TAG_DESC_SET;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.AddTagCommand;
public class AddTagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddTagCommand.MESSAGE_USAGE);

    private static final String MESSAGE_INVALID_TAG = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddTagCommand.MESSAGE_TAG_CONSTRAINTS_VIOLATED);

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified, 1 valid tag
        assertParseFailure(parser, VALID_TAG_HUSBAND, MESSAGE_INVALID_FORMAT);

        // no tag specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no tag specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TAG_HUSBAND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TAG_HUSBAND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTag_failure() {
        //invalid tag input, non-alphanumeric characters
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, MESSAGE_INVALID_TAG);

        //invalid tag input, tag contains space
        assertParseFailure(parser, "1" + INVALID_TAG_DESC_SPACE, MESSAGE_INVALID_TAG);

        //invalid tag input, tag is empty
        assertParseFailure(parser, "1" + INVALID_TAG_DESC_EMPTY, MESSAGE_INVALID_TAG);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_SET;
        AddTagCommand addTagCommand = new AddTagCommand(targetIndex, SET_OF_VALID_TAGS);

        assertParseSuccess(parser, userInput, addTagCommand);
    }

    @Test
    public void parse_multipleRepeatedTags_success() {
        //adding duplicate tags will not do anything to the tags as they are contained in a set
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_SET + TAG_DESC_SET + TAG_DESC_SET;
        AddTagCommand addTagCommand = new AddTagCommand(targetIndex, SET_OF_VALID_TAGS);
        assertParseSuccess(parser, userInput, addTagCommand);
    }

}
