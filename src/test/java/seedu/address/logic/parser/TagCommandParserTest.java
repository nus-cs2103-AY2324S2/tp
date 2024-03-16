package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.model.tag.Tag;

class TagCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private static final Tag TAG1 = new Tag("friend");
    private static final Tag TAG2 = new Tag("husband");

    private static final Collection<Tag> TAGS = List.of(TAG1, TAG2);

    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_validArgs_returnsTagCommand() {
        var index = INDEX_FIRST_PERSON;
        assertParseSuccess(parser, index.getOneBased() + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                new TagCommand(index, TAGS));

        // order should not matter
        assertParseSuccess(parser, index.getOneBased() + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new TagCommand(index, TAGS));

        // one tag
        assertParseSuccess(parser, index.getOneBased() + TAG_DESC_HUSBAND,
                new TagCommand(index, List.of(TAG2)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no tags given
        assertParseFailure(parser, "1",
                MESSAGE_INVALID_FORMAT);

        // no index given
        assertParseFailure(parser, "t/friend",
                MESSAGE_INVALID_FORMAT);
    }
}
