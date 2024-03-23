package seedu.hirehub.logic.parser;

import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.hirehub.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.hirehub.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.hirehub.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.hirehub.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hirehub.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hirehub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hirehub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hirehub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hirehub.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.commands.TagCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.tag.Tag;

public class TagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;

        Set<Tag> tags = ParserUtil.parseTags(Arrays.asList(VALID_TAG_FRIEND, VALID_TAG_HUSBAND));

        TagCommand expectedCommand = new TagCommand(targetIndex, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
