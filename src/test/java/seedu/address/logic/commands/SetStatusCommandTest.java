package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

class SetStatusCommandTest {
    private Person taskProject = new PersonBuilder().build();

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
