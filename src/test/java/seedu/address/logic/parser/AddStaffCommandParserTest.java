package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMMISSION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPLOYMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNDETECTED_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOBSTAFF;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.messages.Messages;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Staff;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandParserTest {
    private AddStaffCommandParser parser = new AddStaffCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Staff expectedPerson = new StaffBuilder(BOBSTAFF).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB, new AddStaffCommand(expectedPerson));
    }


    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB;

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

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + ADDRESS_DESC_AMY + SALARY_DESC_AMY + EMPLOYMENT_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_SALARY, PREFIX_EMPLOYMENT));

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

        // invalid salary
        assertParseFailure(parser, validExpectedPersonString + INVALID_SALARY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SALARY));

        // invalid employment
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMPLOYMENT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMPLOYMENT));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("name");
        String exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        undetectedFields = new ArrayList<>();
        undetectedFields.add("phone");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                expectedMessage);

        // missing email prefix
        undetectedFields = new ArrayList<>();
        undetectedFields.add("email");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                expectedMessage);

        // missing address prefix
        undetectedFields = new ArrayList<>();
        undetectedFields.add("address");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                expectedMessage);

        // missing salary prefix
        undetectedFields = new ArrayList<>();
        undetectedFields.add("salary");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_SALARY_BOB + EMPLOYMENT_DESC_BOB,
                expectedMessage);

        // missing employment prefix
        undetectedFields = new ArrayList<>();
        undetectedFields.add("employment");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + VALID_EMPLOYMENT_BOB,
                expectedMessage);

        // all prefixes missing
        undetectedFields = new ArrayList<>();
        undetectedFields.add("name");
        undetectedFields.add("phone");
        undetectedFields.add("email");
        undetectedFields.add("address");
        undetectedFields.add("salary");
        undetectedFields.add("employment");
        exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
        expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_SALARY_BOB + VALID_EMPLOYMENT_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_SALARY_DESC + EMPLOYMENT_DESC_BOB,
                Salary.MESSAGE_CONSTRAINTS);

        // invalid employment
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + SALARY_DESC_BOB + INVALID_EMPLOYMENT_DESC,
                Employment.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + INVALID_SALARY_DESC + INVALID_EMPLOYMENT_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidField_failure() {
        String exception = String.format(MESSAGE_INVALID_FIELD_FORMAT, "[commission]");
        exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);
        assertParseFailure(parser, NAME_DESC_BOB + COMMISSION_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + SALARY_DESC_BOB + EMPLOYMENT_DESC_BOB,
                exception);
    }
}
