package seedu.address.logic.commands;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;

/**
 * Filters the employee list based on the given criteria.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    private final Predicate<Employee> predicate;
    private final String filterDescription;

    /**
     * Creates a FilterCommand to filter the employee list based on the given criteria.
     *
     * @param predicate The predicate to filter the employee list.
     * @param filterDescription The description of the filter criteria.
     */
    public FilterCommand(Predicate<Employee> predicate, String filterDescription) {
        this.predicate = predicate;
        this.filterDescription = filterDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredEmployeeList(predicate);

        if (model.getFilteredEmployeeList().isEmpty()) {
            throw new CommandException("No employees found matching the criteria: " + filterDescription);
        }

        return new CommandResult("Filtered list based on: " + filterDescription);
    }

    /**
     * Returns the predicate used to filter the employee list.
     * @return The predicate used to filter the employee list.
     */
    public Predicate<Employee> getPredicate() {
        return predicate;
    }
}
