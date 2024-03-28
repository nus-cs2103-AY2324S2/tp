package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueId;

/**
 * Deletes a employee identified using it's displayed index from the address
 * book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by the index number used in the displayed employee list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted Employee: %1$s";
    public static final String MESSAGE_DELETE_EMPLOYEE_DUPLICATE = "Multiple employees with the same name found. "
            + "Please use the unique ID to delete the employee.";

    private final Index targetIndex;
    private final String targetName;
    private final UniqueId uid;

    /**
     * Null constructor for DeleteCommand
     */
    public DeleteCommand() {
        this.targetIndex = null;
        this.targetName = null;
        this.uid = null;
    }


    /**
     * Constructor for index-based deletion
     *
     * @param targetIndex index of the employee in the filtered employee list to
     *                    delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetName = null; // Name is not used in this context
        this.uid = null; // UniqueId is not used in this context
    }

    /**
     * Constructor for name-based deletion
     *
     * @param targetName name of the employee to delete
     */
    public DeleteCommand(String targetName) {
        this.targetIndex = null; // Index is not used in this context
        this.targetName = targetName;
        this.uid = null; // UniqueId is not used in this context
    }

    /**
     * Constructor for unique id-based deletion
     *
     * @param uid unique id of the employee to delete
     */
    public DeleteCommand(UniqueId uid) {
        this.targetIndex = null; // Index is not used in this context
        this.targetName = null; // Name is not used in this context
        this.uid = uid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex != null) {
            // Index-based deletion logic remains the same
            return deleteByIndex(model);
        } else if (targetName != null && !targetName.isEmpty()) {
            // Implement name-based deletion logic
            return deleteByName(model);
        } else if (uid != null) {
            // Implement unique id-based deletion logic
            return deleteByUid(model);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Deletes a employee by index
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the index is invalid
     */
    private CommandResult deleteByIndex(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteEmployee(employeeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employeeToDelete)));
    }

    /**
     * Deletes a employee by name
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the employee is not found
     */
    CommandResult deleteByName(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        List<Employee> employeesWithTargetName = new ArrayList<>();

        for (Employee employee : lastShownList) {
            if (employee.getName().fullName.equalsIgnoreCase(targetName)) {
                employeesWithTargetName.add(employee);
            }
        }

        if (employeesWithTargetName.size() > 1) {
            throw new CommandException("Multiple employees with this name found. Please delete by uid.\n"
                    + "Format: delete uid/UID_NUMBER\n" + "Example: delete uid/101");
        } else if (employeesWithTargetName.size() == 1) {
            model.deleteEmployee(employeesWithTargetName.get(0));
            return new CommandResult(
                    String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employeesWithTargetName.get(0))));
        } else {
            throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
        }
    }

    /**
     * Deletes an employee by unique id
     *
     * @param model the model to execute the command
     * @return the result of the command
     * @throws CommandException if the employee is not found
     */
    private CommandResult deleteByUid(Model model) throws CommandException {
        List<Employee> lastShownList = model.getFilteredEmployeeList();
        for (Employee employee : lastShownList) {
            assert uid != null;
            if (employee.getUid().getUidValue().equals(uid.getUidValue())) {
                model.deleteEmployee(employee);
                return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employee)));
            }
        }
        throw new CommandException(Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        // Check if both commands have the same index
        boolean isIndexEqual = targetIndex != null
                && otherDeleteCommand.targetIndex != null
                && targetIndex.equals(otherDeleteCommand.targetIndex);

        // Check if both commands have the same UID
        boolean isUidEqual = uid != null
                && otherDeleteCommand.uid != null
                && uid.equals(otherDeleteCommand.uid);

        boolean isNameEqual = targetName != null
                && otherDeleteCommand.targetName != null
                && targetName.equalsIgnoreCase(otherDeleteCommand.targetName);

        return isIndexEqual || isUidEqual || isNameEqual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
