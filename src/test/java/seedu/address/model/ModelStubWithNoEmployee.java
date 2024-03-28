package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;

/**
 * A Model stub that contains no employees.
 */
public class ModelStubWithNoEmployee extends ModelStub {
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return false;
    }

    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return employees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        // The list remains empty because there are no employees to filter.
    }
}

