package seedu.address.testutil;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task TASK_1 = new Task("task1");
    public static final Task TASK_2 = new Task("task2");
    public static final Task TASK_3 = new Task("task3");

    private static TaskList tasks = new TaskList();

    public static TaskList get() {
        return tasks;
    }

    /**
     * Returns an {@code TaskList} with all the sample tasks.
     */
    public static TaskList getTypicalTaskList() {
        tasks.addTask(TASK_1);
        tasks.addTask(TASK_2);
        tasks.addTask(TASK_3);
        return TypicalTasks.get();
    }
}
