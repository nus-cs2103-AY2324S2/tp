package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE_MATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AddSkillCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkImportantCommand;
import seedu.address.logic.commands.RateMateCommand;
import seedu.address.logic.commands.RequireSkillCommand;
import seedu.address.logic.commands.SuggestMateCommand;
import seedu.address.logic.commands.UnmarkImportantCommand;
import seedu.address.logic.commands.UnrequireSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.ContainsGroupKeywordPredicate;
import seedu.address.testutil.AddSkillDescriptorBuilder;
import seedu.address.testutil.CourseMateBuilder;
import seedu.address.testutil.CourseMateUtil;
import seedu.address.testutil.DeleteSkillDescriptorBuilder;
import seedu.address.testutil.EditCourseMateDescriptorBuilder;

public class MatchMateParserTest {

    private final MatchMateParser parser = new MatchMateParser();

    @Test
    public void parseCommand_add() throws Exception {
        CourseMate courseMate = new CourseMateBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CourseMateUtil.getAddCommand(courseMate));
        assertEquals(new AddCommand(courseMate), command);
    }

    @Test
    public void parseCommand_addSkill() throws Exception {
        CourseMate courseMate = new CourseMateBuilder().withSkills(VALID_SKILL_JAVA).build();
        AddSkillCommand.AddSkillDescriptor descriptor = new AddSkillDescriptorBuilder(courseMate).build();
        AddSkillCommand command = (AddSkillCommand) parser.parseCommand(AddSkillCommand.COMMAND_WORD + " #"
                + INDEX_FIRST_COURSE_MATE.getOneBased() + " "
                + CourseMateUtil.getAddSkillDescriptorDetails(descriptor));
        assertEquals(new AddSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " #" + INDEX_FIRST_COURSE_MATE.getOneBased());
        assertEquals(new DeleteCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE)), command);
    }

    @Test
    public void parseCommand_deleteSkill() throws Exception {
        CourseMate courseMate = new CourseMateBuilder().withSkills(VALID_SKILL_JAVA).build();
        DeleteSkillCommand.DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder(courseMate).build();
        DeleteSkillCommand command = (DeleteSkillCommand) parser.parseCommand(DeleteSkillCommand.COMMAND_WORD + " #"
                + INDEX_FIRST_COURSE_MATE.getOneBased() + " "
                + CourseMateUtil.getDeleteSkillDescriptorDetails(descriptor));
        assertEquals(new DeleteSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), descriptor), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        CourseMate courseMate = new CourseMateBuilder().build();
        EditCommand.EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder(courseMate).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " #"
                + INDEX_FIRST_COURSE_MATE.getOneBased() + " "
                + CourseMateUtil.getEditCourseMateDescriptorDetails(descriptor));
        assertEquals(new EditCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String keyword = "foo bar baz";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FindCommand(new ContainsKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_markImportant() throws Exception {
        assertTrue(parser.parseCommand(MarkImportantCommand.COMMAND_WORD + " CS2103T Group -s Java")
                instanceof MarkImportantCommand);
    }

    @Test
    public void parseCommand_unmarkImportant() throws Exception {
        assertTrue(parser.parseCommand(UnmarkImportantCommand.COMMAND_WORD + " CS2103T Group -s Java")
                instanceof UnmarkImportantCommand);
    }

    @Test
    public void parseCommand_createGroup() throws Exception {
        assertTrue(parser.parseCommand(CreateGroupCommand.COMMAND_WORD + " CS2103T Group")
                instanceof CreateGroupCommand);
    }

    @Test
    public void parseCommand_addMember() throws Exception {
        assertTrue(parser.parseCommand(AddMemberCommand.COMMAND_WORD + " CS2103T Group -cm John Doe")
                instanceof AddMemberCommand);
    }

    @Test
    public void parseCommand_deleteMember() throws Exception {
        assertTrue(parser.parseCommand(DeleteMemberCommand.COMMAND_WORD + " CS2103T Group -cm John Doe")
                instanceof DeleteMemberCommand);
    }

    @Test
    public void parseCommand_editGroup() throws Exception {
        assertTrue(parser.parseCommand(EditGroupCommand.COMMAND_WORD
                + " CS2103T Group -t https://t.me/+3Jh9eXVeRh7qoaIN")
                instanceof EditGroupCommand);
    }

    @Test
    public void parseCommand_findGroup() throws Exception {
        String keyword = "foo bar baz";
        FindGroupCommand command = (FindGroupCommand) parser.parseCommand(
                FindGroupCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FindGroupCommand(new ContainsGroupKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_rateMate() throws Exception {
        assertTrue(parser.parseCommand(RateMateCommand.COMMAND_WORD + " Bob -r 3")
                instanceof RateMateCommand);
    }

    @Test
    public void parseCommand_requireSkill() throws Exception {
        assertTrue(parser.parseCommand(RequireSkillCommand.COMMAND_WORD + " Group 1 -s C++")
                instanceof RequireSkillCommand);
    }

    @Test
    public void parseCommand_suggestMate() throws Exception {
        assertTrue(parser.parseCommand(SuggestMateCommand.COMMAND_WORD + " Group 1")
                instanceof SuggestMateCommand);
    }

    @Test
    public void parseCommand_unrequireSkill() throws Exception {
        assertTrue(parser.parseCommand(UnrequireSkillCommand.COMMAND_WORD + " Group 1 -s C++")
                instanceof UnrequireSkillCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_noSpaceAfterPrefix_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand("add Amy Bee -p11111111 -e amy@example.com"));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
