package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADMISSION_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADMISSION_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WARD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.WARD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WARD_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_DIABETES).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TAG_DESC_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB, new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + TAG_DESC_FALL_RISK + TAG_DESC_DIABETES
                + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + IC_DESC_BOB + DOB_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB + TAG_DESC_DIABETES;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple fields repeated
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_BOB
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DOB_DESC_AMY + IC_DESC_AMY
                       + ADMISSION_DATE_DESC_AMY + WARD_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, TAG_DESC_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                        + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + TAG_DESC_FALL_RISK + TAG_DESC_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid ic
        assertParseFailure(parser, NAME_DESC_BOB
                + TAG_DESC_FALL_RISK + TAG_DESC_DIABETES + DOB_DESC_BOB + INVALID_IC_DESC
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB, Ic.MESSAGE_CONSTRAINTS);

        // invalid dob
        assertParseFailure(parser, NAME_DESC_BOB
                + TAG_DESC_FALL_RISK + TAG_DESC_DIABETES + INVALID_DOB_DESC + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB, Dob.MESSAGE_CONSTRAINTS);

        // invalid ward
        assertParseFailure(parser, NAME_DESC_BOB
                + TAG_DESC_FALL_RISK + TAG_DESC_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + INVALID_WARD_DESC, Ward.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_TAG_DESC
                + DOB_DESC_BOB + IC_DESC_BOB + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TAG_DESC_FALL_RISK
                        + TAG_DESC_DIABETES + DOB_DESC_BOB + IC_DESC_BOB
                + ADMISSION_DATE_DESC_BOB + WARD_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
