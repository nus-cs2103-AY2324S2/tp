package seedu.address.model;

import java.util.ArrayList;
import java.util.Collection;

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
     * Deletes a task based on the index of list.
     *
     * @param index The index of the task to be deleted in the list.
     */
    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    public ObservableList<Task> getSerializeTaskList() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(taskList));
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }

    public boolean isValidTaskIndex(int index) {
        return taskList.size() > index;
    }

}
