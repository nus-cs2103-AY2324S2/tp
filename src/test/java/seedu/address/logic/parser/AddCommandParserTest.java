package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REFLECTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDIO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MATRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MATRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REFLECTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REFLECTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDIO_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDIO_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REFLECTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDIO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INSTRUCTOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.student.Matric;
import seedu.address.model.student.Reflection;
import seedu.address.model.student.Studio;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_STUDENT).withMatric(VALID_MATRIC_NUMBER_BOB)
                .withReflection(VALID_REFLECTION_BOB)
                .withStudio(VALID_STUDIO_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MATRIC_DESC_BOB + REFLECTION_DESC_BOB + STUDIO_DESC_BOB
                + VALID_DESC_STUDENT,
                new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(
                VALID_TAG_FRIEND, VALID_TAG_HUSBAND, VALID_TAG_STUDENT).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MATRIC_DESC_BOB + REFLECTION_DESC_BOB + STUDIO_DESC_BOB
                + VALID_DESC_STUDENT,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MATRIC_DESC_BOB + REFLECTION_DESC_BOB + STUDIO_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple matric numbers
        assertParseFailure(parser, MATRIC_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MATRIC_NUMBER));

        // multiple reflections
        assertParseFailure(parser, REFLECTION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REFLECTION));

        // multiple studios
        assertParseFailure(parser, STUDIO_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDIO));

        // multiple fields repeated
        assertParseFailure(parser, PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NAME_DESC_AMY + ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid matric number
        assertParseFailure(parser, INVALID_MATRIC_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MATRIC_NUMBER));

        // invalid reflection
        assertParseFailure(parser, INVALID_REFLECTION_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REFLECTION));

        // invalid studio
        assertParseFailure(parser, INVALID_STUDIO_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDIO));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid matric number
        assertParseFailure(parser, validExpectedPersonString + INVALID_MATRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MATRIC_NUMBER));

        // invalid reflection
        assertParseFailure(parser, validExpectedPersonString + INVALID_REFLECTION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REFLECTION));

        // invalid studio
        assertParseFailure(parser, validExpectedPersonString + INVALID_STUDIO_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDIO));
    }

    @Test
    public void parse_tagMissing_success() {
        // zero tags; all other fields present means student
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_STUDENT)
                .withMatric(VALID_MATRIC_NUMBER_AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + MATRIC_DESC_AMY + REFLECTION_DESC_AMY + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_matricMissing_success() {
        // no matric number
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_FRIEND).withMatric("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + REFLECTION_DESC_AMY + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_reflectionMissing_success() {
        // no reflection means TA
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_FRIEND, VALID_TAG_TA)
                .withMatric(VALID_MATRIC_NUMBER_AMY).withReflection("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + MATRIC_DESC_AMY + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_studioMissing_success() {
        // no studio means TA
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_FRIEND, VALID_TAG_TA)
                .withMatric(VALID_MATRIC_NUMBER_AMY).withStudio("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + MATRIC_DESC_AMY + REFLECTION_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_matricAndReflectionMissing_success() {
        // no matric number; no reflection
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_FRIEND)
                .withMatric("").withReflection("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }


    @Test
    public void parse_tagAndStudioMissing_success() {
        // zero tags; no studio
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_TA).withMatric(VALID_MATRIC_NUMBER_AMY)
                .withStudio("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + MATRIC_DESC_AMY + REFLECTION_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_tagAndMatricMissing_success() {
        // zero tags; no matric number
        Person expectedPerson = new PersonBuilder(AMY).withTags().withMatric("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + REFLECTION_DESC_AMY + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_tagAndMatricAndReflectionMissing_success() {
        // zero tags; no matric number; no reflection
        Person expectedPerson = new PersonBuilder(AMY).withTags().withMatric("").withReflection("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + STUDIO_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_tagAndMatricAndStudioMissing_success() {
        // zero tags; no matric number; no studio
        Person expectedPerson = new PersonBuilder(AMY).withTags()
                .withMatric("").withStudio("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                           + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + REFLECTION_DESC_AMY,
                           new AddCommand(expectedPerson));
    }

    @Test
    public void parse_matricAndStudioAndReflectionMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).withTags(VALID_TAG_INSTRUCTOR)
                .withMatric("").withReflection("").withStudio("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid matric number
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_MATRIC_DESC, Matric.MESSAGE_CONSTRAINTS);

        // invalid reflection
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_REFLECTION_DESC, Reflection.MESSAGE_CONSTRAINTS);


        // invalid studio
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_STUDIO_DESC, Studio.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
