package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.orders.DeleteOrderCommand;

public class AddOrderCommandParserTest {
    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_missingPrefix_throwsParseException() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1", expectedMessage);
    }

    @Test
    public void check_sameCurrentTime_success() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        AddOrderCommandParser.getCurrentTime();
        assertEquals(AddOrderCommandParser.getCurrentTime(), now.format(formatter));
    }



}
