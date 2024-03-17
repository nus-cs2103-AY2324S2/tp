package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static vitalconnect.testutil.TypicalPersons.BOB_CONTACT;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.DeleteContactCommand;
import vitalconnect.model.person.Person;
import vitalconnect.testutil.PersonBuilder;

public class DeleteContactCommandParserTest {
    private DeleteContactCommandParser parser = new DeleteContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB_CONTACT).withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB,
            new DeleteContactCommand(expectedPerson.getIdentificationInformation().getNric()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactCommand.MESSAGE_USAGE);

        // missing nric prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_NRIC_BOB,
            expectedMessage);
    }
}
