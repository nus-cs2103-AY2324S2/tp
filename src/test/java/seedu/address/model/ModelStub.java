package seedu.address.model;


import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.employee.Employee;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEmployee(Employee employee) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEmployee(Employee target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
