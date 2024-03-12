package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.task.Task;

/**
 * A class that stores the tasks from users.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor of the class.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the end of list.
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }
}

