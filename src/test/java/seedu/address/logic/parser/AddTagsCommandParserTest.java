package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class AddTagsCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagsCommand.MESSAGE_USAGE);

    private AddTagsCommandParser parser = new AddTagsCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddTagsCommand.MESSAGE_NOT_ADDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_inValidTag_failure() {
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validFields_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        List<String> tagList = List.of(VALID_TAG_FRIEND, VALID_TAG_HUSBAND);
        AddTagsCommand expectedCommand = new AddTagsCommand(targetIndex, TagBuilder.build(tagList));

        String userInput1 = targetIndex.getOneBased() + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        assertParseSuccess(parser, userInput1, expectedCommand);

        String userInput2 = targetIndex.getOneBased() + TAG_DESC_FRIEND + "\n  \t" + TAG_DESC_HUSBAND + "\t";
        assertParseSuccess(parser, userInput2, expectedCommand);
    }

}
