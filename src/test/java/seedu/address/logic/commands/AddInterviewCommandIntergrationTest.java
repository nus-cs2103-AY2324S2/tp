package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;


public class AddInterviewCommandIntergrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newInterview_success() {
        Interview validInterview = new InterviewBuilder().buildInterview();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addInterview(validInterview);

        assertCommandSuccess(new AddInterviewCommand(validInterview.getDescription(),
                        validInterview.getApplicant().getPhone(), validInterview.getInterviewer().getPhone(),
                        validInterview.getDate(), validInterview.getStartTime(), validInterview.getEndTime()), model,
                String.format(AddInterviewCommand.MESSAGE_SUCCESS, Messages.formatInterview(validInterview)),
                expectedModel);
    }

    @Test
    public void execute_duplicateInterview_throwsCommandException() {
    }
}
