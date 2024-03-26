package seedu.address.model.internship;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.InternshipAddTaskCommand;

/**
 * Represents a list of tasks in the internship book.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object from an existing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the taskList with ArrayList<Task> type. Primarily for JSON purposes.
     */
    public ArrayList<Task> getArrayListTaskList() {
        return taskList;
    }

    /**
     * Constructs a TaskList object from a string representation of a task list.
     */
    public TaskList(String taskListString) {
        if (taskListString.equals("[]") || taskListString.isEmpty()) {
            this.taskList = new ArrayList<>();
        } else {
            ArrayList<Task> taskList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\{\"task\": \"(.*?)\", \"deadline\": \"(.*?)\"\\}");
            Matcher matcher = pattern.matcher(taskListString);

            while (matcher.find()) {
                String task = matcher.group(1).trim();
                String deadline = matcher.group(2).trim();
                if (deadline.isEmpty()) {
                    taskList.add(new Task(task));
                } else {
                    taskList.add(new Task(task, deadline));
                }
            }
            this.taskList = taskList;
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        // Index is 1-based
        return taskList.get(index - 1);
    }

    /**
     * Returns the size of the task list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Returns a copy of the task list.
     */
    public TaskList copy() {
        ArrayList<Task> copiedTaskList = new ArrayList<>();
        for (Task task : taskList) {
            copiedTaskList.add(task);
        }
        return new TaskList(copiedTaskList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            builder.append(count++).append(". ").append(task.toString()).append("\n");
        }
        return builder.toString();
    }

    /**
     * Converts the task list to a JSON string.
     * @return JSON string representation of the task list
     */
    public String convertToJsonString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Task task : taskList) {
            builder.append(task.convertToJsonString()).append(",");
        }
        if (taskList.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipAddTaskCommand)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) other;
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).equals(otherTaskList.getTask(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
