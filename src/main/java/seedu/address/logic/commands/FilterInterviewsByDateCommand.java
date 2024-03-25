package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Filters the displayed interview list based on the date provided.
 */
public class FilterInterviewsByDateCommand extends FilterCommand {

    public static final String COMMAND_WORD = "filter_interviews_by_date";

    public static final String MESSAGE_SUCCESS = "Listed all interviews by date";

    private LocalDate targetDate;

    public FilterInterviewsByDateCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Interview> interviewList = model.getFilteredInterviewList();
        interviewList = filterInterviewsByDate(interviewList, targetDate);
        if (interviewList.isEmpty()) {
            return new CommandResult("No interviews found on " + targetDate);

        }
        String result = "";
        int x = 1;
        for (Interview i : interviewList) {
            result = result + "\n" + x + ") " + (i.toString()) + "\n";
            x++;
        }
        return new CommandResult(result);
    }

    private List<Interview> filterInterviewsByDate(List<Interview> interviews, LocalDate date) {
        List<Interview> filteredList = new ArrayList<Interview>();
        for (int i = 0; i < interviews.size(); i++) {
            if (interviews.get(i).getDate().equals(date)) {
                filteredList.add(interviews.get(i));
            }
        }
        return filteredList;
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
