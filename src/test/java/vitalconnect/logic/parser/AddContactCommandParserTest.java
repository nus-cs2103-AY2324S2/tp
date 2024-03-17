package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static vitalconnect.testutil.TypicalPersons.BOB_CONTACT;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.AddContactCommand;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.testutil.PersonBuilder;

public class AddContactCommandParserTest {
    private AddContactCommandParser parser = new AddContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB_CONTACT).withTags(VALID_TAG_FRIEND).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
            + ADDRESS_DESC_BOB, new AddContactCommand(expectedPerson.getIdentificationInformation().getNric(),
              expectedPerson.getContactInformation()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE);

        // missing nric prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_NRIC_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + VALID_EMAIL_BOB,
            Nric.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NRIC_DESC_BOB
            + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NRIC_DESC_BOB
            + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NRIC_DESC_BOB
            + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_PHONE_DESC + INVALID_EMAIL_DESC,
            Phone.MESSAGE_CONSTRAINTS);
    }
}
