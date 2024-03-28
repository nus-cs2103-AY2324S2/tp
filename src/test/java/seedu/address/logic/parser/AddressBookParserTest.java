package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersonsUuid.ALICE_UUID;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAttributeCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.relationship.AddRelationshipCommand;
import seedu.address.logic.relationship.DeleteRelationshipCommand;
import seedu.address.logic.relationship.EditRelationshipCommand;
import seedu.address.logic.relationship.ListRelationshipTypesCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new Person(new Attribute[0]);
        person.updateAttribute(new NameAttribute("Name", "Amy Bee"));
        AddCommand command = (AddCommand) parser.parseCommand(
                AddCommand.COMMAND_WORD
                        + " /Name Amy Bee");
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Name", "Amy Bee");
        assertEquals(new AddCommand(attributes), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + ALICE_UUID);
        assertEquals(new DeleteCommand(ALICE_UUID), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_addAttribute() throws Exception {
        String userInput = "addAttribute 4000 \\name John";
        Command command = parser.parseCommand(userInput);

        assertTrue(command instanceof AddAttributeCommand);
    }

    @Test
    public void parseCommand_delAttribute() throws Exception {
        String userInput = "deleteAttribute 4000 /name";
        Command command = parser.parseCommand(userInput);

        assertTrue(command instanceof DeleteAttributeCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_editRelationship() throws Exception {
        String userInput = "editRelation 1234 5678 family friend";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof EditRelationshipCommand);
    }

    @Test
    public void parseCommand_deleteRelationship() throws Exception {
        String userInput = "deleteRelation 1234 5678 siblings";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof DeleteRelationshipCommand);
    }

    @Test
    public void parseCommand_listRelationshipTypes() throws Exception {
        Command command = parser.parseCommand(ListRelationshipTypesCommand.COMMAND_WORD);
        assertTrue(command instanceof ListRelationshipTypesCommand);
    }

    @Test
    public void parseCommand_addRelationship() throws Exception {
        String userInput = "addRelation 1234 5678 housemates";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof AddRelationshipCommand);
    }
}
