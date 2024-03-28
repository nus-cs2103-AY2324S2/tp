package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;

/**
 * Sorts all students in the address book based on any given field
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Sorted all persons by %s in %s order.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts all students in the address book "
            + "based on any given field (case-insensitive) of the student in either ascending or descending order"
            + "Parameters: <Field>(email, major, name, phone, star) <asc/desc> \n"
            + "Example: " + COMMAND_WORD + " name" + " asc";
    private String field;
    private boolean isAscending;

    /**
     * Creates a SortCommand to sort students by field {@code field}
     * in either ascending or descending order {@code isAscending}
     * @param field The field in persons that is being used for sorting
     * @param isAscending A boolean flag for the sort if its ascending or descending
     */
    public SortCommand(String field, boolean isAscending) {
        this.field = field.toLowerCase();
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedStudentListByField(field, isAscending);
        return new CommandResult(String.format(MESSAGE_SUCCESS, field, isAscending ? "ascending" : "descending"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return field.equals(otherSortCommand.field) && isAscending == otherSortCommand.isAscending;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("field", field)
                .add("isAscending", isAscending)
                .toString();
    }
}
