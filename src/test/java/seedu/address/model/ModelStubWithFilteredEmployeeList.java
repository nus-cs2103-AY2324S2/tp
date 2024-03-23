package seedu.address.model;

import static seedu.address.testutil.TypicalEmployees.getTypicalEmployees;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;

/**
 * A Model stub that contains a filtered list of employees.
 */
public class ModelStubWithFilteredEmployeeList extends ModelStub {
    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private ObservableList<Employee> filteredEmployees = FXCollections.observableArrayList();

    /**
     * Initializes a ModelStubWithFilteredEmployeeList with the given list of employees.
     */
    public ModelStubWithFilteredEmployeeList() {
        employees.addAll(getTypicalEmployees());
        filteredEmployees.addAll(employees);
    }

    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredEmployees;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        filteredEmployees = employees.stream().filter(predicate).collect(Collectors
                .toCollection(FXCollections::observableArrayList));
    }
    public void resetData() {
        filteredEmployees = FXCollections.observableArrayList(employees);
    }
}
