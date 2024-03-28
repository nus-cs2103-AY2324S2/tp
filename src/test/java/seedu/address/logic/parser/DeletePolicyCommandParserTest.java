package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICYID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeletePolicyCommandParserTest {

    private final DeletePolicyCommandParser parser = new DeletePolicyCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePolicyCommand() throws ParseException {
        String args = "1 " + PREFIX_POLICYID + "123";
        String policytoDelete = "123";
        DeletePolicyCommand expectedCommand = new DeletePolicyCommand(ParserUtil.parseIndex("1"),
                policytoDelete);
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        // Missing index
        String args = PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingPolicyIdPrefix_throwsParseException() {
        // Missing policyId prefix
        String args = "1 ";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        // Invalid index format
        String args = "invalid " + PREFIX_POLICYID + "123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidPolicyIdFormat_throwsParseException() {
        // Invalid policyId format
        String args = "1 " + PREFIX_POLICYID + "a123";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
