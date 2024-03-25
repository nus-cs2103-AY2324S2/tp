package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

class SetDeadlineCommandTest {
    private Person taskProject = new PersonBuilder().build();

    @Test
    public void equals() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        SetDeadlineCommand setDeadlineA = new SetDeadlineCommand("Mar 22 2024", task, project);
        SetDeadlineCommand setDeadlineB = new SetDeadlineCommand("Mar 20 2023", task, project);

        // same object -> returns true
        assertTrue(setDeadlineA.equals(setDeadlineA));

        // same values -> returns true
        SetDeadlineCommand setDeadlineACopy = new SetDeadlineCommand("Mar 22 2024", task, project);
        assertTrue(setDeadlineA.equals(setDeadlineACopy));

        // different types -> returns false
        assertFalse(setDeadlineA.equals(1));

        // null -> returns false
        assertFalse(setDeadlineA.equals(null));

        // different person -> returns false
        assertFalse(setDeadlineA.equals(setDeadlineB));
    }

    @Test
    public void toStringMethod() {
        Task code = new Task("Code");
        String deadline = "Mar 23 2024";
        SetDeadlineCommand setDeadlineCommand = new SetDeadlineCommand(deadline, code, taskProject);
        String expected = SetDeadlineCommand.class.getCanonicalName() + "{setDeadline=" + deadline + "}";
        assertEquals(expected, setDeadlineCommand.toString());
    }
}
