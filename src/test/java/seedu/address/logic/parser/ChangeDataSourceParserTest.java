package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ChangeDataSourceCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.IdMatchesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TagMatchesPredicate;
import seedu.address.model.tag.Tag;


class ChangeDataSourceParserTest {

    private ChangeDataSourceParser parser = new ChangeDataSourceParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeDataSourceCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "addressbook", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeDataSourceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ChangeDataSourceCommand expectedCommand =
                new ChangeDataSourceCommand(Paths.get("data/ab2.json"));

        assertParseSuccess(parser, "data/ab2.json", expectedCommand);
    }
}
