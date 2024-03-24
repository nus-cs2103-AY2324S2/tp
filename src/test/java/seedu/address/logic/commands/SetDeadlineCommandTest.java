package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

class SetDeadlineCommandTest {
    private Person taskProject = new PersonBuilder().build();

    @Test
    public void toStringMethod() {
        Task code = new Task("Code");
        String deadline = "Mar 23 2024";
        SetDeadlineCommand setDeadlineCommand = new SetDeadlineCommand(deadline, code, taskProject);
        String expected = SetDeadlineCommand.class.getCanonicalName() + "{setDeadline=" + deadline + "}";
        assertEquals(expected, setDeadlineCommand.toString());
    }
}
