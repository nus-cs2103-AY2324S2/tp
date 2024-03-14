package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;

class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    void execute() {
    }

    @Test
    void testEquals() {
        Task testTask1 = new Task("test1");
        Task testTask2 = new Task("test2");
        AddTaskCommand addTaskCommand1 = new AddTaskCommand(testTask1);
        AddTaskCommand addTaskCommand2 = new AddTaskCommand(testTask2);

        // same object -> returns true
        assertTrue(addTaskCommand1.equals(addTaskCommand1));

        // different types -> returns false
        assertFalse(addTaskCommand1.equals(1));

        // null -> returns false
        assertFalse(addTaskCommand1.equals(null));

        // different tasks -> returns false
        assertFalse(addTaskCommand1.equals(addTaskCommand2));
    }

    @Test
    void testToString() {
        Task test = new Task("test");
        AddTaskCommand addTaskCommand = new AddTaskCommand(test);
        String expected = AddTaskCommand.class.getCanonicalName() + "{toAdd=" + test + "}";
        assertEquals(expected, addTaskCommand.toString());
    }

}
