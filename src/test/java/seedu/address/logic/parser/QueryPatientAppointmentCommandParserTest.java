package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.QueryPatientAppointmentCommand;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;

public class QueryPatientAppointmentCommandParserTest {
    private QueryPatientAppointmentCommandParser parser = new QueryPatientAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                QueryPatientAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsQueryCommand() {
        // no leading and trailing whitespaces
        QueryPatientAppointmentCommand expectedQueryCommand =
                new QueryPatientAppointmentCommand(new AppointmentContainsPatientPredicate(
                        Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedQueryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedQueryCommand);
    }
}
