package seedu.address.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
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

    @SuppressWarnings("unchecked")
    public void setTaskList(TaskList tasks) {
        taskList.addAll((Collection<? extends Task>) tasks);
    }

    /**
     * Adds a task to the end of list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns a task based on the index of list.
     *
     * @param index The index of the task to be returned.
     */
    public Task getTask(Index index) {
        Task task = taskList.get(index.getZeroBased());
        return task;
    }

    /**
     * Deletes a task based on the index of list.
     *
     * @param index The index of the task to be deleted in the list.
     */
    public Task deleteTask(Index index) {
        Task task = taskList.get(index.getZeroBased());
        taskList.remove(index.getZeroBased());
        return task;
    }

    public ObservableList<Task> getSerializeTaskList() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(taskList));
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }

    public boolean isValidTaskIndex(Index index) {
        return index.getZeroBased() < taskList.size();
    }

}
