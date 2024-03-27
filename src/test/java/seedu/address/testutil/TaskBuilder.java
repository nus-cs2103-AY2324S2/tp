package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Implement Test";
    public static final String DEFAULT_TASK_DESCRIPTION = "Test to test the code";
    public static final String DEFAULT_TASK_DEADLINE = "Empty";

    private TaskName taskName;
    private TaskDescription taskDescription;
    private TaskDeadline taskDeadline;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
        taskDeadline = new TaskDeadline();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getName();
        taskDescription = taskToCopy.getDescription();
        taskDeadline = taskToCopy.getDeadline();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDeadline(String taskDeadline) {
        this.taskDeadline = new TaskDeadline(taskDeadline);
        return this;
    }

    public Task build() {
        return new Task(taskName, taskDescription, new TaskStatus(), taskDeadline);
    }
}
