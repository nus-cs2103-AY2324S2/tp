package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.person.Rating;

public class RateCommandParserTest {
    private final RateCommandParser parser = new RateCommandParser();
    private final Rating validRating = new Rating("0");

    @Test
    public void parse_validArgs_returnsRateCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + RATING_DESC_BOB,
                new RateCommand(BOB.getName(), validRating));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INVALID_RATING_DESC,
                String.format(RateMessages.MESSAGE_RATE_INVALID_RATING, Rating.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_nameMissing_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RATING_DESC_BOB,
                String.format(RateMessages.MESSAGE_RATE_MISSING_NAME, RateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_ratingMissing_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB,
                String.format(RateMessages.MESSAGE_RATE_MISSING_RATING, RateCommand.MESSAGE_USAGE));
    }
}
