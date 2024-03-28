package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TERMSOFSERVICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Skills;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no id specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, ID_DESC_AMY, EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidId_failure() {
        // negative id
        assertParseFailure(parser, "i/-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero id
        assertParseFailure(parser, "i/0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "i/1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, ID_DESC_AMY + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code
        // Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                ID_DESC_AMY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                ID_DESC_AMY + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                ID_DESC_AMY + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                ID_DESC_AMY + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Id targetId = Id.generateId(VALID_ID_AMY);
        String userInput = ID_DESC_AMY + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Id targetId = Id.generateId(VALID_ID_AMY);
        String userInput = ID_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Id targetId = Id.generateId(VALID_ID_AMY);
        String userInput = ID_DESC_AMY + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = ID_DESC_AMY + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = ID_DESC_AMY + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = ID_DESC_AMY + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = ID_DESC_AMY + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Id targetId = ID_FIRST_PERSON;
        String userInput = targetId + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetId + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetId + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = targetId + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        Id targetId = Id.generateId(VALID_ID_AMY);
        String userInput = ID_DESC_AMY + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parseOptionalFields_allFieldsPresent_success() throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + PREFIX_PREFERENCES + "pref1 pref2 "
                + PREFIX_PRODUCTS + "product1 " + PREFIX_PRODUCTS + "product2 " + PREFIX_DEPARTMENT + "dept "
                + PREFIX_JOBTITLE + "title " + PREFIX_SKILLS + "skill1 " + PREFIX_SKILLS + "skill2 "
                + PREFIX_TERMSOFSERVICE + "terms");

        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        descriptor.setPreferences(ParserUtil.parsePreferences("pref1 pref2"));
        descriptor.setProducts(ParserUtil.parseProducts(Arrays.asList("product1", "product2")));
        descriptor.setDepartment(ParserUtil.parseDepartment("dept"));
        descriptor.setJobTitle(ParserUtil.parseJobTitle("title"));
        descriptor.setSkills(ParserUtil.parseSkills(Arrays.asList("skill1", "skill2")));
        descriptor.setTermsOfService(ParserUtil.parseTermsOfService("terms"));

        parser.parseOptionalFields(argMultimap, descriptor);

        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor();
        expectedDescriptor.setPreferences(ParserUtil.parsePreferences("pref1 pref2"));
        expectedDescriptor.setProducts(ParserUtil.parseProducts(Arrays.asList("product1", "product2")));
        expectedDescriptor.setDepartment(ParserUtil.parseDepartment("dept"));
        expectedDescriptor.setJobTitle(ParserUtil.parseJobTitle("title"));
        expectedDescriptor.setSkills(ParserUtil.parseSkills(Arrays.asList("skill1", "skill2")));
        expectedDescriptor.setTermsOfService(ParserUtil.parseTermsOfService("terms"));

        assertEquals(expectedDescriptor, descriptor);


        Products expectedProducts = new Products(Arrays.asList("product1", "product2"));
        assertEquals(expectedProducts, descriptor.getProducts().get());

        assertEquals(new Department("dept"), descriptor.getDepartment().get());
        assertEquals(new JobTitle("title"), descriptor.getJobTitle().get());

        Skills expectedSkills = new Skills();
        expectedSkills.addSkills(new HashSet<>(Arrays.asList("skill1", "skill2")));
        assertEquals(expectedSkills, descriptor.getSkills().get());

        assertEquals(new TermsOfService("terms"), descriptor.getTermsOfService().get());
    }

    @Test
    public void parseOptionalFields_someFieldsPresent_success() throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + PREFIX_PREFERENCES + "pref1 pref2 "
                + PREFIX_DEPARTMENT + "dept " + PREFIX_SKILLS + "skill1 " + PREFIX_SKILLS + "skill2");

        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        descriptor.setPreferences(ParserUtil.parsePreferences("pref1 pref2"));
        descriptor.setDepartment(ParserUtil.parseDepartment("dept"));
        descriptor.setSkills(ParserUtil.parseSkills(Arrays.asList("skill1", "skill2")));

        parser.parseOptionalFields(argMultimap, descriptor);

        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor();
        expectedDescriptor.setPreferences(ParserUtil.parsePreferences("pref1 pref2"));
        expectedDescriptor.setDepartment(ParserUtil.parseDepartment("dept"));
        expectedDescriptor.setSkills(ParserUtil.parseSkills(Arrays.asList("skill1", "skill2")));

        assertEquals(expectedDescriptor, descriptor);
    }

    @Test
    public void parseOptionalFields_noFieldsPresent_success() throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize("");

        EditPersonDescriptor descriptor = new EditPersonDescriptor();

        parser.parseOptionalFields(argMultimap, descriptor);

        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor();

        assertEquals(expectedDescriptor, descriptor);

        assertTrue(descriptor.getPreferences().isEmpty());
        assertTrue(descriptor.getProducts().isEmpty());
        assertNull(descriptor.getDepartment().orElse(null));
        assertNull(descriptor.getJobTitle().orElse(null));
        assertTrue(descriptor.getSkills().isEmpty());
        assertNull(descriptor.getTermsOfService().orElse(null));
    }
}
