package seedu.address.logic.relationship;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class AddRelationshipCommandParserTest {
    private Model model;
    private AddRelationshipCommandParser parser = new AddRelationshipCommandParser();
    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    void parse_validInput_success() throws Exception {
        String userInput = "0001 0003 family";
        AddRelationshipCommand expected = new AddRelationshipCommand("0001", "0003", "family");
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    void parseInvalidInput_throwsParseException() {
        String userInput = "0001 19000 family";
        assertParseFailure(parser, userInput, "The UUID provided is invalid.");
    }
    @Test
    void parse_invalidInputMissingParts_throwsIllegalArgumentException() {
        String userInput = "0001 family";
        assertParseFailure(parser, userInput, "You have missing arguments");
    }
}
