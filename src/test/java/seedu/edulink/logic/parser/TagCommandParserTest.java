package seedu.edulink.logic.parser;

import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.TagCommand;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.tag.Tag;

public class TagCommandParserTest {

    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_validArgSingleTag_returnsTagCommand() {
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(new Tag("topstudent"));

        assertParseSuccess(parser, " id/A9014616L t/topstudent",
            new TagCommand(new Id("A9014616L"), expectedTags));
    }

    @Test
    public void parse_validArgMultiTag_returnsTagCommand() {
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(new Tag("topstudent"));
        expectedTags.add(new Tag("potentialTA"));

        assertParseSuccess(parser, " id/A9014616L t/topstudent t/potentialTA",
            new TagCommand(new Id("A9014616L"), expectedTags));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        assertParseFailure(parser, " id/A9014616L t/top student",
            Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        assertParseFailure(parser, " id/A9016L t/topstudent",
            Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIdTag_throwsParseException() {
        assertParseFailure(parser, " id/A9016L t/top student",
            Id.MESSAGE_CONSTRAINTS);
    }

}
