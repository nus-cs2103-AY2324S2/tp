package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

class SetStatusCommandTest {
    private Person taskProject = new PersonBuilder().build();

    @Test
    public void equals() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        SetStatusCommand setStatusComplete = new SetStatusCommand("complete", task, project);
        SetStatusCommand setStatusIncomplete = new SetStatusCommand("incomplete", task, project);

        // same object -> returns true
        assertTrue(setStatusComplete.equals(setStatusComplete));

        // same values -> returns true
        SetStatusCommand setStatusCompleteCopy = new SetStatusCommand("complete", task, project);
        assertTrue(setStatusComplete.equals(setStatusCompleteCopy));

        // different types -> returns false
        assertFalse(setStatusComplete.equals(1));

        // null -> returns false
        assertFalse(setStatusComplete.equals(null));

        // different person -> returns false
        assertFalse(setStatusComplete.equals(setStatusIncomplete));
    }

    @Test
    public void toStringMethod() {
        Task code = new Task("Code");
        String statusComplete = "complete";
        SetStatusCommand setStatusCommandComplete = new SetStatusCommand("complete", code, taskProject);
        String expectedComplete = SetStatusCommand.class.getCanonicalName() + "{set status=" + statusComplete + "}";
        assertEquals(expectedComplete, setStatusCommandComplete.toString());

        String statusIncomplete = "incomplete";
        SetStatusCommand setStatusCommandIncomplete = new SetStatusCommand("incomplete", code, taskProject);
        String expectedIncomplete = SetStatusCommand.class.getCanonicalName() + "{set status=" + statusIncomplete + "}";
        assertEquals(expectedIncomplete, setStatusCommandIncomplete.toString());

    }
}
