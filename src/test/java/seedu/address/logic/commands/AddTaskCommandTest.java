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
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

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
        Task validTask = new Task(new TaskName("Implement test"),
                new TaskDescription("Test to test the code"),
                new TaskStatus());

        CommandResult commandResult = new AddTaskCommand(validTask).execute(model);

        assertTrue(model.hasTask(validTask));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() throws CommandException {
        Task validTask = new Task(new TaskName("Implement test"),
                new TaskDescription("Test to test the code"),
                new TaskStatus());
        new AddTaskCommand(validTask).execute(model);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> new AddTaskCommand(validTask).execute(model));
    }

    @Test
    void testEquals() {
        Task testTask1 = new Task(new TaskName("Implement test1"),
                new TaskDescription("First test to test the code"),
                new TaskStatus());
        Task testTask2 = new Task(new TaskName("Implement test2"),
                new TaskDescription("First test to test the code"),
                new TaskStatus());
        Task testTask3 = new Task(new TaskName("Implement test1"),
                new TaskDescription("Second test to test the code"),
                new TaskStatus());
        Task testTask4 = new Task(new TaskName("Implement test1"),
                new TaskDescription("First test to test the code"),
                new TaskStatus(),
                new TaskDeadline("12-12-2024 16:00"));
        Task testTask5 = new Task(new TaskName("Implement test1"),
                new TaskDescription("First test to test the code"),
                new TaskStatus(),
                new TaskDeadline("12-12-2024 18:00"));
        AddTaskCommand addTaskCommand1 = new AddTaskCommand(testTask1);
        AddTaskCommand addTaskCommand2 = new AddTaskCommand(testTask2);
        AddTaskCommand addTaskCommand3 = new AddTaskCommand(testTask3);
        AddTaskCommand addTaskCommand4 = new AddTaskCommand(testTask4);
        AddTaskCommand addTaskCommand5 = new AddTaskCommand(testTask5);

        // same object -> returns true
        assertTrue(addTaskCommand1.equals(addTaskCommand1));

        // same values -> returns true
        AddTaskCommand addTaskCommandCopy1 = new AddTaskCommand(testTask1);
        assertTrue(addTaskCommandCopy1.equals(addTaskCommand1));

        // different types -> returns false
        assertFalse(addTaskCommand1.equals(1));

        // null -> returns false
        assertFalse(addTaskCommand1.equals(null));

        // different tasks -> returns false
        assertFalse(addTaskCommand1.equals(addTaskCommand2));

        // different description -> returns false
        assertFalse(addTaskCommand1.equals(addTaskCommand3));

        // different deadline -> returns false
        assertFalse(addTaskCommand1.equals(addTaskCommand4));

        // different deadline -> returns false
        assertFalse(addTaskCommand4.equals(addTaskCommand5));
    }

    @Test
    void testToString() {
        Task test = new Task(new TaskName("Implement test"),
                new TaskDescription("Test to test the code"),
                new TaskStatus());
        AddTaskCommand addTaskCommand = new AddTaskCommand(test);
        String expected = AddTaskCommand.class.getCanonicalName() + "{toAdd=" + test + "}";
        assertEquals(expected, addTaskCommand.toString());
    }

}
