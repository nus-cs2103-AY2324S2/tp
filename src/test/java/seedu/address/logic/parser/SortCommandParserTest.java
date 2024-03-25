package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.SortCriteria;
import seedu.address.model.person.SortOrder;

public class SortCommandParserTest {
    public static final String VALID_SORT_NAME_ASC = SortCriteria.NAME + " " + PREFIX_SORT_ORDER + SortOrder.ASC;
    public static final String VALID_SORT_NAME_DESC = SortCriteria.NAME + " " + PREFIX_SORT_ORDER + SortOrder.DESC;
    public static final String VALID_SORT_PHONE_ASC = SortCriteria.PHONE + " " + PREFIX_SORT_ORDER + SortOrder.ASC;
    public static final String VALID_SORT_PHONE_DESC = SortCriteria.PHONE + " " + PREFIX_SORT_ORDER + SortOrder.DESC;
    public static final String VALID_SORT_EMAIL_ASC = SortCriteria.EMAIL + " " + PREFIX_SORT_ORDER + SortOrder.ASC;
    public static final String VALID_SORT_EMAIL_DESC = SortCriteria.EMAIL + " " + PREFIX_SORT_ORDER + SortOrder.DESC;
    public static final String VALID_SORT_ADDRESS_ASC = SortCriteria.ADDRESS + " " + PREFIX_SORT_ORDER + SortOrder.ASC;
    public static final String VALID_SORT_ADDRESS_DESC = SortCriteria.ADDRESS + " " + PREFIX_SORT_ORDER
            + SortOrder.DESC;
    public static final String VALID_SORT_PRIORITY_ASC = SortCriteria.PRIORITY + " " + PREFIX_SORT_ORDER
            + SortOrder.ASC;
    public static final String VALID_SORT_PRIORITY_DESC = SortCriteria.PRIORITY + " " + PREFIX_SORT_ORDER
            + SortOrder.DESC;
    public static final String VALID_SORT_BIRTHDAY_ASC = SortCriteria.BIRTHDAY + " " + PREFIX_SORT_ORDER
            + SortOrder.ASC;
    public static final String VALID_SORT_BIRTHDAY_DESC = SortCriteria.BIRTHDAY + " " + PREFIX_SORT_ORDER
            + SortOrder.DESC;
    public static final String VALID_SORT_LASTMET_ASC = SortCriteria.LASTMET + " " + PREFIX_SORT_ORDER + SortOrder.ASC;
    public static final String VALID_SORT_LASTMET_DESC = SortCriteria.LASTMET + " " + PREFIX_SORT_ORDER
            + SortOrder.DESC;
    public static final String VALID_SORT_SCHEDULE_ASC = SortCriteria.SCHEDULE + " " + PREFIX_SORT_ORDER
            + SortOrder.ASC;
    public static final String VALID_SORT_SCHEDULE_DESC = SortCriteria.SCHEDULE + " " + PREFIX_SORT_ORDER
            + SortOrder.DESC;

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_NAME_ASC,
                new SortCommand(SortCriteria.NAME, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_NAME_DESC,
                new SortCommand(SortCriteria.NAME, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_PHONE_ASC,
                new SortCommand(SortCriteria.PHONE, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_PHONE_DESC,
                new SortCommand(SortCriteria.PHONE, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_EMAIL_ASC,
                new SortCommand(SortCriteria.EMAIL, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_EMAIL_DESC,
                new SortCommand(SortCriteria.EMAIL, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_ADDRESS_ASC,
                new SortCommand(SortCriteria.ADDRESS, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_ADDRESS_DESC,
                new SortCommand(SortCriteria.ADDRESS, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_PRIORITY_ASC,
                new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_PRIORITY_DESC,
                new SortCommand(SortCriteria.PRIORITY, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_BIRTHDAY_ASC,
                new SortCommand(SortCriteria.BIRTHDAY, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_BIRTHDAY_DESC,
                new SortCommand(SortCriteria.BIRTHDAY, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_LASTMET_ASC,
                new SortCommand(SortCriteria.LASTMET, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_LASTMET_DESC,
                new SortCommand(SortCriteria.LASTMET, SortOrder.DESC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_SCHEDULE_ASC,
                new SortCommand(SortCriteria.SCHEDULE, SortOrder.ASC));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SORT_SCHEDULE_DESC,
                new SortCommand(SortCriteria.SCHEDULE, SortOrder.DESC));

        // without whitespace preamble
        assertParseSuccess(parser, VALID_SORT_NAME_ASC, new SortCommand(SortCriteria.NAME, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_NAME_DESC, new SortCommand(SortCriteria.NAME, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_PHONE_ASC, new SortCommand(SortCriteria.PHONE, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_PHONE_DESC, new SortCommand(SortCriteria.PHONE, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_EMAIL_ASC, new SortCommand(SortCriteria.EMAIL, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_EMAIL_DESC, new SortCommand(SortCriteria.EMAIL, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_ADDRESS_ASC, new SortCommand(SortCriteria.ADDRESS, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_ADDRESS_DESC, new SortCommand(SortCriteria.ADDRESS, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_PRIORITY_ASC, new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_PRIORITY_DESC, new SortCommand(SortCriteria.PRIORITY, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_BIRTHDAY_ASC, new SortCommand(SortCriteria.BIRTHDAY, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_BIRTHDAY_DESC, new SortCommand(SortCriteria.BIRTHDAY, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_LASTMET_ASC, new SortCommand(SortCriteria.LASTMET, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_LASTMET_DESC, new SortCommand(SortCriteria.LASTMET, SortOrder.DESC));
        assertParseSuccess(parser, VALID_SORT_SCHEDULE_ASC, new SortCommand(SortCriteria.SCHEDULE, SortOrder.ASC));
        assertParseSuccess(parser, VALID_SORT_SCHEDULE_DESC, new SortCommand(SortCriteria.SCHEDULE, SortOrder.DESC));
    }

    @Test
    public void parse_repeatedParameter_failure() {
        // multiple sort orders
        assertParseFailure(parser, VALID_SORT_NAME_ASC + " " + PREFIX_SORT_ORDER + SortOrder.DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SORT_ORDER));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing sort criteria
        assertParseFailure(parser, " " + PREFIX_SORT_ORDER + SortOrder.ASC, expectedMessage);

        // missing sort order
        assertParseFailure(parser, SortCriteria.NAME.toString(), expectedMessage);
        assertParseFailure(parser, SortCriteria.NAME.toString() + " " + SortOrder.DESC, expectedMessage);

        // missing both sort criteria and sort order
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
public void parse_invalidValue_failure() {
        // invalid sort criteria
        assertParseFailure(parser, "invalid" + " " + PREFIX_SORT_ORDER + SortOrder.ASC,
                SortCriteria.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "x" + " " + PREFIX_SORT_ORDER + SortOrder.DESC,
                SortCriteria.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " name " + VALID_SORT_NAME_ASC,
                SortCriteria.MESSAGE_CONSTRAINTS);

        // invalid sort order
        assertParseFailure(parser, SortCriteria.NAME + " " + PREFIX_SORT_ORDER + "invalid",
                SortOrder.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SortCriteria.NAME + " " + PREFIX_SORT_ORDER + "x",
                SortOrder.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, SortCriteria.NAME + " " + PREFIX_SORT_ORDER,
                SortOrder.MESSAGE_CONSTRAINTS);
    }
}
