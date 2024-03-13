package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.TaskMasterPro;
import seedu.address.model.employee.Employee;

/**
 * An Immutable TaskMasterPro that is serializable to JSON format.
 */
@JsonRootName(value = "taskmasterpro")
class JsonSerializableTaskMasterPro {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";

    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskMasterPro} with the given employees.
     */
    @JsonCreator
    public JsonSerializableTaskMasterPro(@JsonProperty("employees") List<JsonAdaptedEmployee> employees) {
        this.employees.addAll(employees);
    }

    /**
     * Converts a given {@code ReadOnlyTaskMasterPro} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskMasterPro}.
     */
    public JsonSerializableTaskMasterPro(ReadOnlyTaskMasterPro source) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TaskMasterPro into the model's {@code TaskMasterPro} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskMasterPro toModelType() throws IllegalValueException {
        TaskMasterPro taskMasterPro = new TaskMasterPro();
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (taskMasterPro.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            taskMasterPro.addEmployee(employee);
        }
        return taskMasterPro;
    }

}
