package seedu.teachstack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.teachstack.logic.commands.SetWeakThresholdCommand;
import seedu.teachstack.logic.parser.exceptions.ParseException;
import seedu.teachstack.model.person.Grade;

public class SetWeakThresholdCommandParserTest {
    private SetWeakThresholdCommandParser weakParser = new SetWeakThresholdCommandParser();

    @Test
    public void parse_setweakCommand() throws ParseException {
        String args = "B";
        SetWeakThresholdCommand expectedCommand = new SetWeakThresholdCommand(new Grade("B"));
        assertEquals(expectedCommand, weakParser.parse(args));
    }

    @Test
    public void parse_inputWithLeadingTrailingSpaces_success() throws ParseException {
        // Input with leading/trailing spaces
        String args = "   C+   ";
        SetWeakThresholdCommand expectedCommand = new SetWeakThresholdCommand(new Grade("C+"));
        assertEquals(expectedCommand, weakParser.parse(args));
    }

}
