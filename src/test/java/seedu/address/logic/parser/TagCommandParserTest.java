package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_singleTag_success() {
        String user_input = "240001 " + PREFIX_TAG + " Finance";

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Finance"));

        TagCommand expectedCommand = new TagCommand(new Id(240001), tags);

        assertParseSuccess(parser, user_input, expectedCommand);
    }

    @Test
    public void parse_multipleTag_success() {
        String userInput = "240001 "
                + PREFIX_TAG + " Finance "
                + PREFIX_TAG + " Management "
                + PREFIX_TAG + " Family";

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Finance"));
        tags.add(new Tag("Management"));
        tags.add(new Tag("Family"));

        TagCommand expectedCommand = new TagCommand(new Id(240001), tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyId_throwsParseException() {
        String userInput = PREFIX_TAG + " Finance";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        String userInput = "0 " + PREFIX_TAG + " Finance";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

}
