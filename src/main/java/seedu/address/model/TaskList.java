package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskList(TaskList toBeCopied) {
        requireNonNull(toBeCopied);

        setTaskList(toBeCopied);
    }

    public void setTaskList(TaskList tasks) {
        taskList = new ArrayList<>(tasks.getSerializeTaskList());
    }

    /**
     * Adds a task to the end of list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public ObservableList<Task> getSerializeTaskList() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(taskList));
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
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
        return taskList.equals(otherTaskList.taskList);
    }
}
