package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.AddressContainsSubstringPredicate;
import seedu.address.model.person.predicates.CombinedPredicates;
import seedu.address.model.person.predicates.EmailContainsSubstringPredicate;
import seedu.address.model.person.predicates.NameContainsSubstringPredicate;
import seedu.address.model.person.predicates.NoteContainsSubstringPredicate;
import seedu.address.model.person.predicates.PhoneContainsSubstringPredicate;
import seedu.address.model.person.predicates.TagSetContainsAllTagsPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {
    private static final String NAME = "Alice";
    private static final String PHONE = "11111111";
    private static final String EMAIL = "test@example.com";
    private static final String ADDRESS = "street";
    private static final String NOTE = "best";
    private static final String TAG = "friends";

    private static final NameContainsSubstringPredicate NAME_PREDICATE = new NameContainsSubstringPredicate(NAME);
    private static final PhoneContainsSubstringPredicate PHONE_PREDICATE = new PhoneContainsSubstringPredicate(
            PHONE);
    private static final EmailContainsSubstringPredicate EMAIL_PREDICATE = new EmailContainsSubstringPredicate(
            EMAIL);
    private static final AddressContainsSubstringPredicate ADDRESS_PREDICATE = new AddressContainsSubstringPredicate(
            ADDRESS);
    private static final NoteContainsSubstringPredicate NOTE_PREDICATE = new NoteContainsSubstringPredicate(NOTE);
    private static final TagSetContainsAllTagsPredicate TAGS_PREDICATE = new TagSetContainsAllTagsPredicate(
            new HashSet<Tag>(Arrays.asList(new Tag(TAG))));

    private static final NameContainsSubstringPredicate NAME_PREDICATE_EMPTY = new NameContainsSubstringPredicate(
            "");
    private static final PhoneContainsSubstringPredicate PHONE_PREDICATE_EMPTY = new PhoneContainsSubstringPredicate(
            "");
    private static final EmailContainsSubstringPredicate EMAIL_PREDICATE_EMPTY = new EmailContainsSubstringPredicate(
            "");
    private static final AddressContainsSubstringPredicate ADDRESS_PREDICATE_EMPTY =

            new AddressContainsSubstringPredicate("");
    private static final NoteContainsSubstringPredicate NOTE_PREDICATE_EMPTY = new NoteContainsSubstringPredicate(
            "");
    private static final TagSetContainsAllTagsPredicate TAGS_PREDICATE_EMPTY = new TagSetContainsAllTagsPredicate(
            new HashSet<>());

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE, PHONE_PREDICATE_EMPTY, EMAIL_PREDICATE_EMPTY, ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_NAME, NAME), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_NAME, NAME), expectedCommand);
    }

    @Test
    public void parse_phoneFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE_EMPTY, PHONE_PREDICATE, EMAIL_PREDICATE_EMPTY, ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_PHONE, PHONE), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_PHONE, PHONE), expectedCommand);
    }

    @Test
    public void parse_emailFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE_EMPTY, PHONE_PREDICATE_EMPTY, EMAIL_PREDICATE, ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_EMAIL, EMAIL), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_EMAIL, EMAIL), expectedCommand);
    }

    @Test
    public void parse_addressFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE_EMPTY, PHONE_PREDICATE_EMPTY, EMAIL_PREDICATE_EMPTY, ADDRESS_PREDICATE,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_ADDRESS, ADDRESS), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_ADDRESS, ADDRESS), expectedCommand);
    }

    @Test
    public void parse_noteFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE_EMPTY, PHONE_PREDICATE_EMPTY, EMAIL_PREDICATE_EMPTY,
                ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_NOTE, NOTE), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_NOTE, NOTE), expectedCommand);
    }

    @Test
    public void parse_tagFieldPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE_EMPTY, PHONE_PREDICATE_EMPTY, EMAIL_PREDICATE_EMPTY,
                ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE));

        assertParseSuccess(parser, String.format(" %s%s", PREFIX_TAG, TAG), expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n", PREFIX_TAG, TAG), expectedCommand);
    }

    @Test
    public void parse_multipleFieldsPresent_returnsFindCommand() {
        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                NAME_PREDICATE, PHONE_PREDICATE, EMAIL_PREDICATE_EMPTY, ADDRESS_PREDICATE_EMPTY,
                NOTE_PREDICATE_EMPTY, TAGS_PREDICATE_EMPTY));

        assertParseSuccess(parser, String.format(" %s%s %s%s", PREFIX_NAME, NAME, PREFIX_PHONE, PHONE),
                expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, String.format(" %s%s \n %s%s", PREFIX_NAME, NAME, PREFIX_PHONE, PHONE),
                expectedCommand);
    }
}
