package seedu.findvisor.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.commons.util.DateTimeUtil.dateTimeToInputString;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.findvisor.logic.commands.CommandTestUtil.createValidMeeting;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.findvisor.testutil.Assert.assertThrows;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.findvisor.logic.commands.AddCommand;
import seedu.findvisor.logic.commands.ClearCommand;
import seedu.findvisor.logic.commands.DeleteCommand;
import seedu.findvisor.logic.commands.EditCommand;
import seedu.findvisor.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.findvisor.logic.commands.ExitCommand;
import seedu.findvisor.logic.commands.FindCommand;
import seedu.findvisor.logic.commands.HelpCommand;
import seedu.findvisor.logic.commands.ListCommand;
import seedu.findvisor.logic.commands.ScheduleCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;
import seedu.findvisor.model.person.EmailContainsKeywordPredicate;
import seedu.findvisor.model.person.NameContainsKeywordPredicate;
import seedu.findvisor.model.person.Meeting;
import seedu.findvisor.model.person.NameContainsKeywordsPredicate;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.PhoneContainsKeywordPredicate;
import seedu.findvisor.model.tag.TagsContainsKeywordsPredicate;
import seedu.findvisor.testutil.EditPersonDescriptorBuilder;
import seedu.findvisor.testutil.PersonBuilder;
import seedu.findvisor.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // Find using name
        FindCommand findNameCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_AMY);
        assertEquals(new FindCommand(new NameContainsKeywordPredicate(VALID_NAME_AMY)), findNameCommand);

        // Find using email
        FindCommand findEmailCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_EMAIL + VALID_EMAIL_AMY);
        assertEquals(new FindCommand(new EmailContainsKeywordPredicate(VALID_EMAIL_AMY)), findEmailCommand);

        // Find using phone
        FindCommand findPhoneCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_PHONE + VALID_PHONE_AMY);
        assertEquals(new FindCommand(new PhoneContainsKeywordPredicate(VALID_PHONE_AMY)), findPhoneCommand);

        // Find using tags
        FindCommand findTagsCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                + PREFIX_TAG + VALID_TAG_FRIEND + " "
                + PREFIX_TAG + VALID_TAG_HUSBAND);
        assertEquals(new FindCommand(new TagsContainsKeywordsPredicate(
                Arrays.asList(new String[]{VALID_TAG_FRIEND, VALID_TAG_HUSBAND}))), findTagsCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_schedule() throws Exception {
        Meeting meeting = createValidMeeting();
        ScheduleCommand command = (ScheduleCommand) parser.parseCommand(
                ScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_START_DATETIME + dateTimeToInputString(meeting.start) + " "
                + PREFIX_END_DATETIME + dateTimeToInputString(meeting.end));
        assertEquals(new ScheduleCommand(INDEX_FIRST_PERSON, meeting), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
