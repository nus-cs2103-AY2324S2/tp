package seedu.address.logic.parser;

import static seedu.address.logic.Messages.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.SearchCommand.SearchPersonDescriptor;
import seedu.address.model.person.Country;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.SearchPersonDescriptorBuilder;

public class SearchCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE);

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_missingOrAdditionalParts_failure() {
        // nothing specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // preamble with no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // preamble with field specified
        assertParseFailure(parser, "1 n/Alex", MESSAGE_INVALID_FORMAT);

        // duplicate tags
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_COUNTRY_DESC, Country.MESSAGE_CONSTRAINTS); // invalid country
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // duplicate prefixes are captured before invalid values
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_COUNTRY_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + COUNTRY_DESC_AMY + NAME_DESC_AMY;

        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withCountry(VALID_COUNTRY_AMY)
                .withTags(VALID_TAG_HUSBAND).build();

        SearchCommand expectedCommand = new SearchCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + EMAIL_DESC_AMY;

        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        SearchCommand expectedCommand = new SearchCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        SearchCommand expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = PHONE_DESC_AMY;
        descriptor = new SearchPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY;
        descriptor = new SearchPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // country
        userInput = COUNTRY_DESC_AMY;
        descriptor = new SearchPersonDescriptorBuilder().withCountry(VALID_COUNTRY_AMY).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = STATUS_DESC_AMY;
        descriptor = new SearchPersonDescriptorBuilder().withStatus(VALID_STATUS_AMY).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // comment
        userInput = COMMENT_DESC_AMY;
        descriptor = new SearchPersonDescriptorBuilder().withComment(VALID_COMMENT_AMY).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = TAG_DESC_FRIEND;
        descriptor = new SearchPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new SearchCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        String userInput = INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = PHONE_DESC_AMY + COUNTRY_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + COUNTRY_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + COUNTRY_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COUNTRY, PREFIX_TAG));

        // multiple invalid values
        userInput = INVALID_PHONE_DESC + INVALID_COUNTRY_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_COUNTRY_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COUNTRY));
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = TAG_EMPTY;
        SearchPersonDescriptor descriptor = new SearchPersonDescriptorBuilder().withTags().build();
        SearchCommand expectedCommand = new SearchCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
