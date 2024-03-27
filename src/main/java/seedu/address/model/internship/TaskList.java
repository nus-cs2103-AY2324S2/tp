package seedu.address.model.internship;

import java.util.ArrayList;

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
     * Adds tasks from a comma-separated string into the task list. Only for testing purposes using TypicalInternships.
     *
     * @param tasksString the string containing comma-separated tasks
     */
    public TaskList(String tasksString) {
        if (tasksString.equals("") || tasksString.equals(" ")) {
            this.taskList = new ArrayList<>();
        } else {
            ArrayList<Task> taskList = new ArrayList<>();
            String[] tasksArray = tasksString.split("; ");
            for (String desc : tasksArray) {
                // Check for a deadline pattern
                if (desc.contains("(") && desc.contains(")")) {
                    String description = desc.substring(0, desc.indexOf("(")).trim();
                    String deadlineStr = desc.substring(desc.indexOf("(") + 1, desc.indexOf(")")).trim();
                    taskList.add(new Task(description, deadlineStr));
                } else {
                    taskList.add(new Task(desc.trim()));
                }
            }
            this.taskList = taskList;
        }
    }

    /**
     * Returns the taskList with {@code ArrayList<Task>} type. Primarily for JSON purposes.
     */
    public ArrayList<Task> getArrayListTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
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
        if (!(other instanceof TaskList)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) other;
        if (this.taskList.size() != otherTaskList.taskList.size()) {
            return false;
        }
        for (int i = 0; i < this.taskList.size(); i++) {
            if (!this.taskList.get(i).equals(otherTaskList.taskList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return taskList.hashCode();
    }
}
