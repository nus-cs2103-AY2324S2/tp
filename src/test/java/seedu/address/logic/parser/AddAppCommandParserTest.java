package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddAppCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppCommandParserTest {

    private final AddAppCommandParser parser = new AddAppCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(BOB_APPT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB,
                new AddAppCommand(expectedAppointment));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedAppointmentString = NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB;

        // multiple NRICs
        assertParseFailure(parser, NRIC_DESC_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple dates
        assertParseFailure(parser, DATE_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple start times
        assertParseFailure(parser, START_TIME_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // multiple end times
        assertParseFailure(parser, END_TIME_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // multiple appointment types
        assertParseFailure(parser, TYPE_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));

        // multiple notes
        assertParseFailure(parser, NOTE_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedAppointmentString + DATE_DESC_APPOINTMENT_AMY + NRIC_DESC_AMY
                        + START_TIME_DESC_APPOINTMENT_AMY + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC, PREFIX_DATE, PREFIX_START_TIME,
                        PREFIX_END_TIME, PREFIX_TAG, PREFIX_NOTE));

        // invalid value followed by valid value

        // invalid NRIC
        assertParseFailure(parser, INVALID_NRIC_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid date
        assertParseFailure(parser, INVALID_DATE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // invalid start time
        assertParseFailure(parser, INVALID_START_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid end time
        assertParseFailure(parser, INVALID_END_TIME_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // invalid appointment type
        assertParseFailure(parser, INVALID_APPOINTMENT_TYPE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));

        // invalid note
        assertParseFailure(parser, INVALID_APPOINTMENT_NOTE_DESC + validExpectedAppointmentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppCommand.MESSAGE_USAGE);

        // missing NRIC prefix
        assertParseFailure(parser, DATE_DESC_APPOINTMENT_BOB + START_TIME_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NRIC_DESC_BOB + START_TIME_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);

        // missing appointment type prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);

        // missing note prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB + VALID_APPOINTMENT_TYPE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NRIC_BOB + VALID_APPOINTMENT_DATE_BOB
                + VALID_APPOINTMENT_START_TIME_BOB + VALID_APPOINTMENT_END_TIME_BOB
                + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid NRIC
        assertParseFailure(parser, INVALID_NRIC_DESC + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB, Nric.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_DATE_DESC
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB, Date.MESSAGE_CONSTRAINTS);

        // invalid time period
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + INVALID_START_TIME_DESC + INVALID_END_TIME_DESC
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB, TimePeriod.MESSAGE_CONSTRAINTS);

        // invalid appointment type
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + INVALID_APPOINTMENT_TYPE_DESC + NOTE_DESC_APPOINTMENT_BOB, AppointmentType.MESSAGE_CONSTRAINTS);

        // invalid note
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + INVALID_APPOINTMENT_NOTE_DESC, Note.MESSAGE_CONSTRAINTS);

        // multiple invalid values, only the first one reported
        assertParseFailure(parser, INVALID_NRIC_DESC + INVALID_DATE_DESC
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                + TYPE_DESC_APPOINTMENT_BOB + NOTE_DESC_APPOINTMENT_BOB, Nric.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB
                        + VALID_APPOINTMENT_TYPE_BOB + VALID_APPOINTMENT_NOTE_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppCommand.MESSAGE_USAGE));
    }
}
