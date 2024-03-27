package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RateMateCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.Rating;

public class RateMateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE);

    private RateMateCommandParser parser = new RateMateCommandParser();

    @Test
    public void parse_invalidPreabmle_failure() {
        // negative index
        assertParseFailure(parser, "#-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "#0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        // empty input
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE));

        // missing coursemate
        assertParseFailure(parser, "-r 4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE));

        // missing prefix
        assertParseFailure(parser, "Alex 4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateMateCommand.MESSAGE_USAGE));

        // invalid rating
        assertParseFailure(parser, "Alex -r 10", Rating.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsRateMateCommand() {
        Name courseMate = new Name("Bob");
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(courseMate);

        RateMateCommand targetCommand = new RateMateCommand(queryableCourseMate, new Rating("5"));
        assertParseSuccess(parser, "Bob -r 5", targetCommand);
    }
}
