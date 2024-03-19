package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.commands.CancelAppCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class CancelAppCommandParserTest {

    private final CancelAppCommandParser parser = new CancelAppCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(BOB_APPT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB,
                new CancelAppCommand(
                        expectedAppointment.getNric(),
                        expectedAppointment.getDate(),
                        expectedAppointment.getTimePeriod()
                ));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelAppCommand.MESSAGE_USAGE);

        // missing NRIC prefix
        assertParseFailure(parser, DATE_DESC_APPOINTMENT_BOB + START_TIME_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NRIC_DESC_BOB + START_TIME_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + END_TIME_DESC_APPOINTMENT_BOB,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NRIC_BOB + VALID_APPOINTMENT_DATE_BOB
                        + VALID_APPOINTMENT_START_TIME_BOB + VALID_APPOINTMENT_END_TIME_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid NRIC
        assertParseFailure(parser, INVALID_NRIC_DESC + DATE_DESC_APPOINTMENT_BOB
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB, Nric.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_DATE_DESC
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB, Date.MESSAGE_CONSTRAINTS);

        // invalid time period
        assertParseFailure(parser, NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                + INVALID_START_TIME_DESC + INVALID_END_TIME_DESC, TimePeriod.MESSAGE_CONSTRAINTS);

        // multiple invalid values, only the first one reported
        assertParseFailure(parser, INVALID_NRIC_DESC + INVALID_DATE_DESC
                + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB, Nric.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NRIC_DESC_BOB + DATE_DESC_APPOINTMENT_BOB
                        + START_TIME_DESC_APPOINTMENT_BOB + END_TIME_DESC_APPOINTMENT_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelAppCommand.MESSAGE_USAGE));
    }
}
