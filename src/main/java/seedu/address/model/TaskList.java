package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.task.Task;

/**
 * A class that stores the tasks from users.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor of the class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the end of list.
     * @param task The task to be added to the list.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }
    
}

