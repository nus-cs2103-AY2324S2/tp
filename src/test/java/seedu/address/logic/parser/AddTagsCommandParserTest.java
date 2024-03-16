package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class AddTagsCommandParserTest {

    private final AddTagsCommandParser parser = new AddTagsCommandParser();

    @Test
    public void parse_validArgs_returnsAddTagsCommand() throws ParseException {
        Index targetIndex = INDEX_SECOND_PATIENT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_DEPRESSION;

        AddTagsCommand expectedCommand = new AddTagsCommand(targetIndex, Set.of(new Tag(VALID_TAG_DEPRESSION)));
        AddTagsCommand command = parser.parse(userInput);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_validArgsMultipleTags_returnsAddTagsCommand() throws ParseException {
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES;

        AddTagsCommand expectedCommand = new AddTagsCommand(targetIndex,
                Set.of(new Tag(VALID_TAG_DEPRESSION), new Tag(VALID_TAG_DIABETES)));
        AddTagsCommand command = parser.parse(userInput);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Empty index
        assertThrows(ParseException.class, () -> parser.parse(""));

        // Invalid tag
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_TAG_DESC));

        // Missing index
        assertThrows(ParseException.class, () -> parser.parse(TAG_DESC_DEPRESSION));

        // Empty or missing tags
        assertThrows(ParseException.class, () -> parser.parse("1"));

        // Only Whitespace tags
        assertThrows(ParseException.class, () -> parser.parse("1  "));

        // Mixed invalid and valid tags
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_TAG_DESC
                + TAG_DESC_DEPRESSION));

        // Multiple invalid tags
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_TAG_DESC
                + INVALID_TAG_DESC));

        // Invalid index
        assertThrows(ParseException.class, () -> parser.parse("a" + TAG_DESC_DEPRESSION));

        // Invalid index followed by valid tag
        assertThrows(ParseException.class, () -> parser.parse("a" + TAG_DESC_DEPRESSION));
    }
}
