package seedu.address.model.attendance;

import seedu.address.commons.core.index.Index;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Week {
    public static final String MESSAGE_CONSTRAINTS = "Week number should be between 1 and 13 (inclusive)";

    private final Index weekIndex;

    public Week(Index weekIndex) { // The parsing into index is done in the parser
        checkArgument(isValidWeek(weekIndex), MESSAGE_CONSTRAINTS);
        this.weekIndex = weekIndex;
    }

    public Index getWeekIndex() {
        return weekIndex;
    }

    public static boolean isValidWeek(Index weekIndex) {
        return weekIndex.getOneBased() >= 1 && weekIndex.getOneBased() <= 13;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Week)) {
            return false;
        }

        Week otherWeek = (Week) other;
        return weekIndex.equals(otherWeek.weekIndex);
    }

    @Override
    public int hashCode() {
        return weekIndex.getOneBased();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "Week " + weekIndex.getOneBased();
    }
}
