package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_TASKID;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TASKID;
import static seedu.address.logic.Messages.MESSAGE_NONEXISTENT_TASKS;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.UnassignTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;

/**
 * Represents an Employee's AssignedTasks in TaskMasterPro.
 * Guarantees: immutable; is valid as declared in {@link #isValidTask(String)}
 */
public class AssignedTasks {

    public static final String MESSAGE_CONSTRAINTS =
            "TASK_IDS should only contain numeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum} ]*$";

    private String tasks;

    private Hashtable<TaskId, Task> assignedTasks;

    /**
     * Constructs a {@code AssignedTasks}.
     *
     * @param tasks A valid name.
     */
    public AssignedTasks(String tasks) {
        requireNonNull(tasks);
        checkArgument(isValidTask(tasks), MESSAGE_CONSTRAINTS);
        this.tasks = tasks;
        this.assignedTasks = new Hashtable<>();
    }

    /**
     * Gets the tasks assigned to the employee.
     *
     * @return A string containing the tasks assigned to the employee.
     */
    public String getTasks() {
        return tasks;
    }

    /**
     * Gets the tasks assigned to the employee.
     *
     * @return A hashtable containing the tasks assigned to the employee.
     */
    public Hashtable<TaskId, Task> getAssignedTasks() {
        return assignedTasks;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * JsonSerializableTaskMasterPro will run this after all Employees and Tasks has been added.
     * The hashtable will be initialized with stored data.
     */
    public void initiateHashTable(List<Task> taskList) {
        if (tasks.equals("")) {
            return;
        }
        String[] taskArray = tasks.split(" ");

        // Check if taskID matches any of the numbers in tasks
        for (String taskId : taskArray) {
            for (Task task : taskList) {
                if (Integer.parseInt(taskId) == task.getTaskId().taskId) {
                    assignedTasks.put(task.getTaskId(), task);
                }
            }
        }
    }

    /**
     * Adds a new task into assignedTasks.
     *
     * @param task The task to be added.
     * @return The updated AssignedTasks object.
     * @throws CommandException If the task ID is already present in the assigned tasks.
     */
    public AssignedTasks assignTask(Task task) throws CommandException {
        if (Objects.equals(tasks, "")) {
            tasks = "" + task.getTaskId().taskId;
            assignedTasks.put(task.getTaskId(), task);
            return this;
        }
        String[] taskArray = tasks.split(" ");

        // Check if taskID matches any of the numbers in tasks
        for (String taskId : taskArray) {
            if (Integer.parseInt(taskId) == task.getTaskId().taskId) {
                throw new CommandException(
                        String.format(MESSAGE_DUPLICATE_TASKID, AssignTaskCommand.MESSAGE_USAGE));
            }
        }

        // Add the taskID to tasks
        tasks += " " + task.getTaskId().taskId;
        tasks.trim();

        if (assignedTasks.get(task.getTaskId()) != null) {
            throw new CommandException(
                    String.format(MESSAGE_DUPLICATE_TASKID, AssignTaskCommand.MESSAGE_USAGE));
        }
        assignedTasks.put(task.getTaskId(), task);
        return this;
    }

    /**
     * Removes a task from assignedTasks.
     * Deletes the specified task from the assigned tasks list.
     * If the specified task ID is not found in the assigned tasks list,
     * or if the assigned tasks list is empty, a CommandException is thrown.
     *
     * @param taskId The task to be removed.
     * @return The updated AssignedTasks object.
     * @throws CommandException If the task ID is not present in the assigned tasks.
     */
    public AssignedTasks unassignTask(TaskId taskId) throws CommandException {
        if (Objects.equals(tasks, "")) {
            throw new CommandException(
                    String.format(MESSAGE_NONEXISTENT_TASKS, UnassignTaskCommand.MESSAGE_USAGE));
        }

        String[] taskArray = tasks.split(" ");
        StringBuilder updatedTasks = new StringBuilder();

        boolean taskFound = false;

        for (String task : taskArray) {
            if (Integer.parseInt(task) == taskId.taskId) {
                taskFound = true;
            } else {
                updatedTasks.append(task).append(" ");
            }
        }
        if (!taskFound) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TASKID, UnassignTaskCommand.MESSAGE_USAGE));
        }
        tasks = updatedTasks.toString().trim();

        if (assignedTasks.get(taskId) == null) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_TASKID, AssignTaskCommand.MESSAGE_USAGE));
        }
        assignedTasks.remove(taskId);
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignedTasks)) {
            return false;
        }

        AssignedTasks otherAssignedTasks = (AssignedTasks) other;
        return tasks.equals(otherAssignedTasks.tasks) && assignedTasks.equals((otherAssignedTasks.assignedTasks));
    }

    @Override
    public String toString() {
        String taskString = "";
        for (TaskId taskId : assignedTasks.keySet()) {
            // Retrieve the Task using the key
            Task task = assignedTasks.get(taskId);

            // Append the Task details to the string
            taskString += (task.getTaskId().toString() + ". " + task.getName() + "\n");
        }
        return taskString;
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
