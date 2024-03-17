package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICYID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICYNAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.policy.Policy;
public class AddPolicyCommandParserTest {

    private final AddPolicyCommandParser parser = new AddPolicyCommandParser();

    @Test
    public void parse_validArgs_returnsAddPolicyCommand() throws ParseException {
        String args = "1 " + PREFIX_POLICYNAME + "Life " + PREFIX_POLICYID + "123";
        Policy policyToAdd = new Policy("Life", "123");
        AddPolicyCommand expectedCommand = new AddPolicyCommand(ParserUtil.parseIndex("1"), policyToAdd);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        // Missing index
        String args = PREFIX_POLICYNAME + "Life " + PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingPolicyNamePrefix_throwsParseException() {
        // Missing policyName prefix
        String args = "1 Life " + PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingPolicyIdPrefix_throwsParseException() {
        // Missing policyId prefix
        String args = "1 " + PREFIX_POLICYNAME + "Life 123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        // Invalid index format
        String args = "invalid " + PREFIX_POLICYNAME + "Life " + PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidPolicyNameFormat_throwsParseException() {
        // Invalid policyName format
        String args = "1 " + PREFIX_POLICYNAME + "#Life " + PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidPolicyIdFormat_throwsParseException() {
        // Invalid policyId format
        String args = "1 " + PREFIX_POLICYNAME + "Life " + PREFIX_POLICYID + "a123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
