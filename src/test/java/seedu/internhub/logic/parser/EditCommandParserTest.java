package seedu.internhub.logic.parser;

import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhub.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERN_DURATION_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.INTERVIEW_DATE_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.internhub.logic.commands.CommandTestUtil.JOB_DESCRIPTION_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.TAG_DESC_INTERVIEW;
import static seedu.internhub.logic.commands.CommandTestUtil.TAG_DESC_NO_REPLY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERN_DURATION_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_JOB_DESCRIPTION_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_TAG_NO_REPLY;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhub.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.internhub.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.internhub.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.Messages;
import seedu.internhub.logic.commands.EditCommand;
import seedu.internhub.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Tag;
import seedu.internhub.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        // Since address is optional, the following testcase is not relevant
        // assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_NO_REPLY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + JOB_DESCRIPTION_DESC_AMY + INTERVIEW_DATE_DESC_AMY
                + INTERN_DURATION_DESC_AMY + SALARY_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_NO_REPLY).withJobDescription(VALID_JOB_DESCRIPTION_AMY)
                .withInterviewDate(VALID_INTERVIEW_DATE_AMY).withInternDuration(VALID_INTERN_DURATION_AMY)
                .withSalary(VALID_SALARY_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tag
        userInput = targetIndex.getOneBased() + TAG_DESC_INTERVIEW;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_INTERVIEW).build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // job description
        userInput = targetIndex.getOneBased() + JOB_DESCRIPTION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withJobDescription(VALID_JOB_DESCRIPTION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview date
        userInput = targetIndex.getOneBased() + INTERVIEW_DATE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // intern duration
        userInput = targetIndex.getOneBased() + INTERN_DURATION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withInternDuration(VALID_INTERN_DURATION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // salary
        userInput = targetIndex.getOneBased() + SALARY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withSalary(VALID_SALARY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()
        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_INTERVIEW + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_INTERVIEW + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_NO_REPLY;

        assertParseFailure(
                parser,
                userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG, PREFIX_ADDRESS)
        );
        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;
        assertParseFailure(
                parser,
                userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)
        );
    }
}
