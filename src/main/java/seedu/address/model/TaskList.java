package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/**
 * A class that stores the tasks from users.
 */
public class TaskList {
    //private ArrayList<Task> taskList;
    private ObservableList<Task> observableList;

    /**
     * Constructor of the class.
     */
    public TaskList() {
        observableList = FXCollections.observableArrayList();
    }

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskList(TaskList toBeCopied) {
        this();

        requireNonNull(toBeCopied);
        setTaskList(toBeCopied);
    }

    public void setTaskList(TaskList tasks) {
        observableList.setAll(tasks.observableList);
    }

    /**
     * Adds a task to the end of list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        observableList.add(task);
    }

    /**
     * Deletes a task based on the index of list.
     *
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        observableList.remove(task);
    }

    public ObservableList<Task> getSerializeTaskList() {
        return observableList;
    }

    public boolean hasTask(Task task) {
        return observableList.contains(task);
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
        return observableList.equals(otherTaskList.observableList);
    }

}
