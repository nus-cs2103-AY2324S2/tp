package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.Status;

/**
 * Filters the displayed person list based on the status provided.
 */
public class FilterPersonsByStatusCommand extends FilterCommand {

    public static final String COMMAND_WORD = FilterCommand.COMMAND_WORD + "_by_status";

    public static final String MESSAGE_SUCCESS = FilterCommand.MESSAGE_SUCCESS + " persons with status";
    private Status targetStatus;

    /**
     * Creates a FilterPersonsByStatusCommand to filter persons by status.
     *
     * @param targetStatus The {@code Status} to be matched
     */
    public FilterPersonsByStatusCommand(Status targetStatus) {
        this.targetStatus = targetStatus;
    }

    /**
     * This method wraps the {@code updateFilteredPersonList} method of the {@code ModelManager} class
     * with a preliminary check on whether any persons exist with the {@code targetStatus}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A command result indicating success
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        boolean existsMatchingPersons = model.getFilteredPersonList().stream()
                .anyMatch(person -> person.getCurrentStatus().equals(targetStatus.toString()));
        if (!existsMatchingPersons) {
            return new CommandResult("No persons found with status: " + targetStatus.toString());
        }

        model.updateFilteredPersonList(person -> person.getCurrentStatus().equals(targetStatus.toString()));
        return new CommandResult(MESSAGE_SUCCESS + ": " + targetStatus.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof FilterPersonsByStatusCommand)) {
            return false;
        }

        FilterPersonsByStatusCommand other = (FilterPersonsByStatusCommand) object;
        return this.targetStatus.equals(other.targetStatus);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetStatus", targetStatus)
                .toString();
    }
}

