package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DRUG_ALLERGY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DRUG_ALLERGY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ILLNESS_DESC_GENETIC;
import static seedu.address.logic.commands.CommandTestUtil.ILLNESS_DESC_INFECTIOUS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTHDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DRUG_ALLERGY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ILLNESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_GENETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_INFECTIOUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.illness.Illness;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withIllnesses(VALID_ILLNESS_INFECTIOUS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DRUG_ALLERGY_DESC_BOB
                + ILLNESS_DESC_INFECTIOUS, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleIllnesses = new PersonBuilder(BOB).withIllnesses(VALID_ILLNESS_INFECTIOUS,
                        VALID_ILLNESS_GENETIC).build();
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DRUG_ALLERGY_DESC_BOB
                + ILLNESS_DESC_INFECTIOUS + ILLNESS_DESC_GENETIC, new AddCommand(expectedPersonMultipleIllnesses));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NRIC_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DRUG_ALLERGY_DESC_BOB
                + ILLNESS_DESC_INFECTIOUS;

        // multiple nrics
        assertParseFailure(parser, NRIC_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple genders
        assertParseFailure(parser, GENDER_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // multiple birthdate
        assertParseFailure(parser, BIRTHDATE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BIRTHDATE));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple drugAllergy
        assertParseFailure(parser, DRUG_ALLERGY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DRUG_ALLERGY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + NRIC_DESC_BOB + NAME_DESC_AMY + GENDER_DESC_AMY
                        + BIRTHDATE_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + DRUG_ALLERGY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC, PREFIX_NAME, PREFIX_GENDER, PREFIX_BIRTHDATE,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DRUG_ALLERGY));

        // invalid value followed by valid value

        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid gender
        assertParseFailure(parser, INVALID_GENDER_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // invalid birthdate
        assertParseFailure(parser, INVALID_BIRTHDATE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BIRTHDATE));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid drugAllergy
        assertParseFailure(parser, INVALID_DRUG_ALLERGY_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DRUG_ALLERGY));

        // valid value followed by invalid value

        // invalid nric
        assertParseFailure(parser, validExpectedPersonString + INVALID_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid gender
        assertParseFailure(parser,  validExpectedPersonString + INVALID_GENDER_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // invalid birthdate
        assertParseFailure(parser, validExpectedPersonString + INVALID_BIRTHDATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BIRTHDATE));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid drugAllergy
        assertParseFailure(parser,  validExpectedPersonString + INVALID_DRUG_ALLERGY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DRUG_ALLERGY));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withIllnesses().build();
        assertParseSuccess(parser, NRIC_DESC_AMY + NAME_DESC_AMY + GENDER_DESC_AMY
                        + BIRTHDATE_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + DRUG_ALLERGY_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing nric prefix
        assertParseFailure(parser,
                VALID_NRIC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS, expectedMessage);

        // missing name prefix
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + VALID_NAME_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                expectedMessage);

        // missing birthdate prefix
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + VALID_BIRTHDATE_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                NRIC_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                NRIC_DESC_BOB + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NRIC_BOB + VALID_NAME_BOB + VALID_GENDER_BOB
                        + VALID_BIRTHDATE_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_DRUG_ALLERGY_BOB + VALID_ILLNESS_INFECTIOUS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid nric
        assertParseFailure(parser,
                INVALID_NRIC_DESC
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Nric.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + INVALID_NAME_DESC + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Name.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NRIC_DESC_BOB
                        + NAME_DESC_BOB + INVALID_GENDER_DESC
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Gender.MESSAGE_CONSTRAINTS);

        // invalid birthDate
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + INVALID_BIRTHDATE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                BirthDate.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Email.MESSAGE_CONSTRAINTS);

        // invalid illness
        assertParseFailure(parser,
                NRIC_DESC_BOB
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + INVALID_ILLNESS_DESC,
                Illness.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_NRIC_DESC
                        + INVALID_NAME_DESC + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                Nric.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + INVALID_NRIC_DESC
                        + NAME_DESC_BOB + GENDER_DESC_BOB
                        + BIRTHDATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + DRUG_ALLERGY_DESC_BOB + ILLNESS_DESC_INFECTIOUS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
