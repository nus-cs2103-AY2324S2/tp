package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;

/**
 * A Model stub that contains employees.
 */
public class ModelStubWithEmployee extends ModelStub {
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    /**
     * Initializes a ModelStubWithEmployee with the given employee.
     * @param employee The employee to be added to the model.
     */
    public ModelStubWithEmployee(List<Employee> employeesList) {
        requireNonNull(employeesList);
        employees.addAll(employeesList);
    }


    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.stream().anyMatch(employee::isSameEmployee);
    }

    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return employees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        ObservableList<Employee> filteredList = employees.stream().filter(predicate)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        employees.setAll(filteredList);
    }
}

