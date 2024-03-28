package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;

/**
 * Filters the displayed interview list based on the date provided.
 */
public class FilterInterviewsByDateCommand extends FilterCommand {

    public static final String COMMAND_WORD = "filter_interviews_by_date";

    public static final String MESSAGE_SUCCESS = "Listed all interviews on";

    private LocalDate targetDate;

    public FilterInterviewsByDateCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean existsMatchingInterviews = model.getFilteredInterviewList().stream()
                .anyMatch(interview -> interview.getDate().equals(targetDate));
        if (!existsMatchingInterviews) {
            return new CommandResult("No interviews found on " + targetDate);

        }
        model.updateFilteredInterviewList(interview -> interview.getDate().equals(targetDate));
        return new CommandResult(MESSAGE_SUCCESS + " " + targetDate);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof FilterInterviewsByDateCommand)) {
            return false;
        }

        FilterInterviewsByDateCommand other = (FilterInterviewsByDateCommand) object;
        return this.targetDate.equals(other.targetDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetDate", targetDate)
                .toString();
    }
}
