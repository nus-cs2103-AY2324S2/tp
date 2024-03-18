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
    private ObservableList<Task> observableList;

    /**
     * Constructor of the class.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        observableList = FXCollections.observableArrayList(taskList);
    }

    @SuppressWarnings("unchecked")
    public void setTaskList(TaskList tasks) {
        taskList.addAll((Collection<? extends Task>) tasks);
        observableList.addAll((Collection<? extends Task>) tasks);
    }

    /**
     * Adds a task to the end of list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
        observableList.add(task);
    }

    public ObservableList<Task> getSerializeTaskList() {
        return observableList;
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }

}
