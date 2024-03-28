package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.autocomplete.AutoComplete;
import seedu.address.logic.autocomplete.AutoCompleteCommand;
import seedu.address.logic.autocomplete.AutoCompleteNusNetId;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.SetCourseCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weeknumber.WeekNumber;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
            DeletePersonCommand.COMMAND_WORD + " " + CommandTestUtil.VALID_NUSNET_AMY);
        assertEquals(new DeletePersonCommand(new NusNet(CommandTestUtil.VALID_NUSNET_AMY)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD
            + " " + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
            FindPersonCommand.COMMAND_WORD + " "
                + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        final NusNet nusNet = new NusNet("e1234567");
        final WeekNumber weekNumber = new WeekNumber("10");
        MarkAttendanceCommand command = (MarkAttendanceCommand) parser.parseCommand(
            MarkAttendanceCommand.COMMAND_WORD + " " + PREFIX_NUSNET
                + nusNet.value + " " + PREFIX_WEEK + weekNumber.value);
        assertEquals(new MarkAttendanceCommand(nusNet, weekNumber), command);
    }

    @Test
    public void parseCommand_unmark() throws Exception {
        final NusNet nusNet = new NusNet("e1234567");
        final WeekNumber weekNumber = new WeekNumber("10");
        UnmarkAttendanceCommand command = (UnmarkAttendanceCommand) parser.parseCommand(
            UnmarkAttendanceCommand.COMMAND_WORD + " " + PREFIX_NUSNET
                + nusNet.value + " " + PREFIX_WEEK + weekNumber.value);
        assertEquals(new UnmarkAttendanceCommand(nusNet, weekNumber), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_setCourse() throws Exception {
        final String code = VALID_COURSE_CODE_CS2103T;
        SetCourseCommand command = (SetCourseCommand) parser.parseCommand(SetCourseCommand.COMMAND_WORD + " "
            + code);
        assertEquals(new SetCourseCommand(new Course(code)), command);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    /**
     * Tests that the parser returns the correct AutoComplete object.
     */
    @Test
    void parseAutoComplete() {
        // No input
        AutoComplete ac = parser.parseAutoComplete("");
        assertEquals(ac.getAutoComplete("arbitrary_input"), "");

        // Test for input that contains only command word and no arguments
        assert(parser.parseAutoComplete("arbitrary_command") instanceof AutoCompleteCommand);

        // Test for input that contains command word and arguments
        ac = parser.parseAutoComplete("arbitrary_command arbitrary_arguments");
        assertEquals(ac.getAutoComplete("arbitrary_input"), "");

        // Test for input that contains NUSNET ID
        ac = parser.parseAutoComplete("arbitrary_command nn/arbitrary_nusnet_id");
        assert (ac instanceof AutoCompleteNusNetId);
    }
}
