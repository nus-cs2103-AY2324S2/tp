package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;

public class DeleteMemberCommandParserTest {
    private DeleteMemberCommandParser parser = new DeleteMemberCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMemberCommand() {
        Name groupName1 = new Name("group 1");
        Set<QueryableCourseMate> courseMates1 = new HashSet<>(List.of(
                new QueryableCourseMate(new Name("Bob"))));

        DeleteMemberCommand targetCommand = new DeleteMemberCommand(groupName1, courseMates1);
        assertParseSuccess(parser, "group 1 -cm Bob", targetCommand);

        Name groupName2 = new Name("group 1");
        Set<QueryableCourseMate> courseMates2 = new HashSet<>(List.of(
                new QueryableCourseMate(new Name("Bob")),
                new QueryableCourseMate(new Name("Alice"))));
        assertParseSuccess(parser, "group 1 -cm Bob -cm Alice",
                new DeleteMemberCommand(groupName2, courseMates2));
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        assertParseFailure(parser, "group 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "group 1 -cm",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "gr@up 1 -cm Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
    }
}
