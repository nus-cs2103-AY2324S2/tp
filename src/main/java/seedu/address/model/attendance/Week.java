package seedu.address.model.attendance;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.index.Index;

/**
 * Represents a single week in a 13 Week Semester in TA Toolkit.
 * Each week is identified by a unique index, between 1 and 13 (inclusive).
 */
public class Week {
    public static final String MESSAGE_CONSTRAINTS = "Week number should be between 1 and 13 (inclusive)";

    private final Index weekIndex;

    /**
     * Constructs a {@code Week}.
     * @param weekIndex A valid week index.
     */
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
