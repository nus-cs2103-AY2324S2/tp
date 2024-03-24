package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddInterviewerStatusCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Person;

public class AddInterviewerStatusCommandParserTest {

    private AddInterviewerStatusCommandParser parser = new AddInterviewerStatusCommandParser();
    private final InterviewerStatus nonEmptyStatus = new InterviewerStatus("free");

    @Test
    public void parse_phoneSpecified_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String userInput = firstPerson.getPhone().value + " " + PREFIX_STATUS + nonEmptyStatus;
        AddInterviewerStatusCommand expectedCommand = new AddInterviewerStatusCommand(firstPerson.getPhone(),
                nonEmptyStatus);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_incompleteFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddInterviewerStatusCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddInterviewerStatusCommand.COMMAND_WORD, expectedMessage);

        // no phone
        assertParseFailure(parser, AddInterviewerStatusCommand.COMMAND_WORD + " " + nonEmptyStatus,
                expectedMessage);
    }

    @Test
    public void parse_invalidValues_failure() {
        // invalid status
        assertParseFailure(parser, VALID_PHONE_AMY + " " + PREFIX_STATUS + "nonsense",
               InterviewerStatus.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, "1" + " " + PREFIX_STATUS + "free", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewerStatusCommand.MESSAGE_USAGE));
    }
}
