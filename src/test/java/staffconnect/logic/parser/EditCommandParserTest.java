package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.AVAILABILITY_DESC_MON;
import static staffconnect.logic.commands.CommandTestUtil.AVAILABILITY_DESC_THUR;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_AVAILABILITY_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_MON;
import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_THUR;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_VENUE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_BOB;
import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static staffconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static staffconnect.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static staffconnect.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import staffconnect.commons.core.index.Index;
import staffconnect.logic.Messages;
import staffconnect.logic.commands.EditCommand;
import staffconnect.logic.commands.EditCommand.EditPersonDescriptor;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Email;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;
import staffconnect.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String AVAILABILITY_EMPTY = " " + PREFIX_AVAILABILITY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

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
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module
        assertParseFailure(parser, "1" + INVALID_FACULTY_DESC,
            Faculty.MESSAGE_CONSTRAINTS); // invalid faculty
        assertParseFailure(parser, "1" + INVALID_VENUE_DESC, Venue.MESSAGE_CONSTRAINTS); // invalid venue
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_AVAILABILITY_DESC,
                Availability.MESSAGE_CONSTRAINTS); // invalid availability

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_AVAILABILITY} alone will reset the availabilities of the
        // {@code Person} being edited, parsing it together with a valid availability results in error
        assertParseFailure(parser,
            "1" + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR + AVAILABILITY_EMPTY,
                Availability.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
            "1" + AVAILABILITY_DESC_MON + AVAILABILITY_EMPTY + AVAILABILITY_DESC_THUR,
                Availability.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
            "1" + AVAILABILITY_EMPTY + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR,
            Availability.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_MODULE_AMY
                + VALID_FACULTY_AMY + VALID_VENUE_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased()
                + NAME_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + MODULE_DESC_AMY
                + FACULTY_DESC_AMY + VENUE_DESC_AMY
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY)
                .withFaculty(VALID_FACULTY_AMY).withVenue(VALID_VENUE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAvailabilities(VALID_AVAILABILITY_MON, VALID_AVAILABILITY_THUR).build();
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

        // module
        userInput = targetIndex.getOneBased() + MODULE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withModule(VALID_MODULE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // faculty
        userInput = targetIndex.getOneBased() + FACULTY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withFaculty(VALID_FACULTY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // venue
        userInput = targetIndex.getOneBased() + VENUE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withVenue(VALID_VENUE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // availabilities
        userInput = targetIndex.getOneBased() + AVAILABILITY_DESC_MON;
        descriptor = new EditPersonDescriptorBuilder().withAvailabilities(VALID_AVAILABILITY_MON).build();
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

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + VENUE_DESC_AMY + EMAIL_DESC_AMY + FACULTY_DESC_AMY
                + MODULE_DESC_AMY + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON + PHONE_DESC_AMY
                + VENUE_DESC_AMY + EMAIL_DESC_AMY + FACULTY_DESC_AMY + MODULE_DESC_AMY + TAG_DESC_FRIEND
                + AVAILABILITY_DESC_MON + PHONE_DESC_BOB + VENUE_DESC_BOB + EMAIL_DESC_BOB + FACULTY_DESC_BOB
                + MODULE_DESC_BOB + TAG_DESC_HUSBAND + AVAILABILITY_DESC_THUR;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_MODULE, PREFIX_FACULTY, PREFIX_VENUE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_VENUE_DESC
                + INVALID_EMAIL_DESC + INVALID_FACULTY_DESC + INVALID_MODULE_DESC
                + INVALID_PHONE_DESC + INVALID_VENUE_DESC
                + INVALID_EMAIL_DESC + INVALID_FACULTY_DESC + INVALID_MODULE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MODULE,
                        PREFIX_FACULTY, PREFIX_VENUE));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
