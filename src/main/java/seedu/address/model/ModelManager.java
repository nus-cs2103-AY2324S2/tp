package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskMasterPro taskMasterPro;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredEmployees;

    private final FilteredList<Task> taskList;

    /**
     * Initializes a ModelManager with the given taskMasterPro and userPrefs.
     */
    public ModelManager(ReadOnlyTaskMasterPro taskMasterPro, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskMasterPro, userPrefs);

        logger.fine("Initializing with task master pro: " + taskMasterPro + " and user prefs " + userPrefs);

        this.taskMasterPro = new TaskMasterPro(taskMasterPro);
        this.userPrefs = new UserPrefs(userPrefs);
        ObservableList<Employee> employeeList = this.taskMasterPro.getEmployeeList();
        ObservableList<Task> taskList = this.taskMasterPro.getTaskList();
        filteredEmployees = new FilteredList<>(employeeList);
        this.taskList = new FilteredList<>(taskList);
    }

    public ModelManager() {
        this(new TaskMasterPro(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskMasterProFilePath() {
        return userPrefs.getTaskMasterProFilePath();
    }

    @Override
    public void setTaskMasterProFilePath(Path taskMasterProFilePath) {
        requireNonNull(taskMasterProFilePath);
        userPrefs.setTaskMasterProFilePath(taskMasterProFilePath);
    }

    //=========== TaskMasterPro ================================================================================

    @Override
    public void setTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro) {
        this.taskMasterPro.resetData(taskMasterPro);
    }

    @Override
    public ReadOnlyTaskMasterPro getTaskMasterPro() {
        return taskMasterPro;
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return taskMasterPro.hasEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee target) {
        taskMasterPro.removeEmployee(target);
    }

    @Override
    public void addEmployee(Employee employee) {
        taskMasterPro.addEmployee(employee);
        updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
    }



    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        taskMasterPro.setEmployee(target, editedEmployee);
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedTaskMasterPro}
     */
    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredEmployees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredEmployees.setPredicate(predicate);
    }


    //=========== Task List Accessors =============================================================

    @Override
    public void addTask(Task task) {
        taskMasterPro.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        taskList.setPredicate(predicate);
    }

    @Override
    public void deleteTask(Task target) {
        taskMasterPro.removeTask(target);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskMasterPro.setTask(target, editedTask);
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return taskList;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return taskMasterPro.equals(otherModelManager.taskMasterPro)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredEmployees.equals(otherModelManager.filteredEmployees);
    }

}
