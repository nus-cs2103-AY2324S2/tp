package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_AMPERSAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_CPP;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCourseMates.AMY;
import static seedu.address.testutil.TypicalCourseMates.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.TelegramHandle;
import seedu.address.testutil.CourseMateBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CourseMate expectedCourseMate = new CourseMateBuilder(BOB)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
                .withSkills(VALID_SKILL_CPP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + SKILL_DESC_CPP, new AddCommand(expectedCourseMate));


        // multiple skills - all accepted
        CourseMate expectedCourseMateMultipleSkills =
                new CourseMateBuilder(BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
                .withSkills(VALID_SKILL_CPP, VALID_SKILL_CSHARP).build();
        assertParseSuccess(parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TELEGRAM_DESC_BOB + SKILL_DESC_CSHARP + SKILL_DESC_CPP,
                new AddCommand(expectedCourseMateMultipleSkills));
    }

    @Test
    public void parse_repeatedNonSkillValue_failure() {
        String validExpectedCourseMateString = VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + SKILL_DESC_CPP;

        // multiple phones
        assertParseFailure(parser, validExpectedCourseMateString + PHONE_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, validExpectedCourseMateString + EMAIL_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple telegram handles
        assertParseFailure(parser, validExpectedCourseMateString + TELEGRAM_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedCourseMateString + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + NAME_DESC_AMY + validExpectedCourseMateString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL, PREFIX_PHONE, PREFIX_TELEGRAM));

        // invalid value followed by valid value

        // invalid email
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + EMAIL_DESC_BOB + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, VALID_NAME_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid telegram handle
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_TELEGRAM_DESC + TELEGRAM_DESC_BOB + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // valid value followed by invalid value

        // invalid email
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_EMAIL_DESC + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC
                        + EMAIL_DESC_BOB + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid telegram handle
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TELEGRAM_DESC_BOB + INVALID_TELEGRAM_DESC + SKILL_DESC_CPP,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero skills
        CourseMate expectedCourseMate = new CourseMateBuilder(AMY).withSkills().build();
        assertParseSuccess(parser, VALID_NAME_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedCourseMate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing phone prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB + VALID_PHONE_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing, all counted as name
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // missing name
        assertParseFailure(parser, PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, INVALID_NAME_AMPERSAND + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CPP, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, VALID_NAME_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CPP, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + SKILL_DESC_JAVA + SKILL_DESC_CPP, Email.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TELEGRAM_DESC + SKILL_DESC_JAVA + SKILL_DESC_CPP, TelegramHandle.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_AMPERSAND + PHONE_DESC_BOB + INVALID_EMAIL_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }
}
