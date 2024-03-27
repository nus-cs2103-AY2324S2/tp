package seedu.internhub.logic.parser;

import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhub.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERN_DURATION_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERN_DURATION_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERVIEW_DATE_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERVIEW_DATE_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_INTERN_DURATION_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_JOB_DESCRIPTION_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.JOB_DESCRIPTION_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.JOB_DESCRIPTION_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.internhub.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internhub.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.TAG_DESC_INTERVIEW;
import static seedu.internhub.logic.commands.CommandTestUtil.TAG_DESC_NO_REPLY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERN_DURATION_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_JOB_DESCRIPTION_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERN_DURATION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhub.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.internhub.logic.Messages;
import seedu.internhub.logic.commands.AddCommand;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;
import seedu.internhub.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERVIEW
                + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, new AddCommand(expectedPerson));

    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERVIEW
                + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB;

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

        // multiple job description
        assertParseFailure(parser, JOB_DESCRIPTION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_JOB_DESCRIPTION));

        // multiple interview date
        assertParseFailure(parser, INTERVIEW_DATE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATE));

        // multiple intern duration
        assertParseFailure(parser, INTERN_DURATION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERN_DURATION));

        // multiple salary
        assertParseFailure(parser, SALARY_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_TAG, PREFIX_JOB_DESCRIPTION, PREFIX_INTERVIEW_DATE, PREFIX_INTERN_DURATION,
                        PREFIX_SALARY));

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

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // missing job description prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_JOB_DESCRIPTION_BOB,
                expectedMessage);

        // missing interview date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_INTERVIEW_DATE_BOB,
                expectedMessage);

        // missing intern duration prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_INTERN_DURATION_BOB,
                expectedMessage);

        // missing salary prefix
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
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, Email.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid job description
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_INTERVIEW + INVALID_JOB_DESCRIPTION_DESC + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + SALARY_DESC_BOB, JobDescription.MESSAGE_CONSTRAINTS);

        // invalid intern duration
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INVALID_INTERN_DURATION_DESC + SALARY_DESC_BOB, InternDuration.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_INTERVIEW + JOB_DESCRIPTION_DESC_BOB + INTERVIEW_DATE_DESC_BOB
                + INTERN_DURATION_DESC_BOB + INVALID_SALARY_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_INTERVIEW + TAG_DESC_NO_REPLY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
