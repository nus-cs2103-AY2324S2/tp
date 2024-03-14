package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;

class AddTaskCommandTest {

    private ModelManager model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }


    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Task validTask = new Task("sample");

        CommandResult commandResult = new AddTaskCommand(validTask).execute(model);

        assertTrue(model.hasTask(validTask));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() throws CommandException {
        Task validTask = new Task("sample");
        new AddTaskCommand(validTask).execute(model);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> new AddTaskCommand(validTask).execute(model));
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
