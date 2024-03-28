package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.QueryDoctorAppointmentCommand;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;

public class QueryDoctorAppointmentCommandParserTest {
    private QueryDoctorAppointmentCommandParser parser = new QueryDoctorAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                QueryDoctorAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsQueryCommand() {
        // no leading and trailing whitespaces
        QueryDoctorAppointmentCommand expectedQueryCommand =
                new QueryDoctorAppointmentCommand(new AppointmentContainsDoctorPredicate(
                        Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedQueryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedQueryCommand);
    }
}
