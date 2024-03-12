package seedu.address.model.person;

import seedu.address.commons.util.DateUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.abs;

/**
 * Represents the last met date with a Client in the address book.
 */
public class LastMet {
    private final LocalDate lastMetDate;
    private boolean isOverdue;
    private static long lastMetDuration = 90;

    /**
     * Constructs a {@code LastMet}.
     *
     * @param lastMet A valid lastMet date.
     */
    public LastMet(LocalDate lastMet) {
            this.lastMetDate = lastMet;
            checkOverdue();
        }

    /**
     * Changes the number of days of lastMet before a Client's LastMet is flagged in the schedule tracker.
     * @param days
     */
    public static void setLastMetDuration(long days) {
        lastMetDuration = days;
    }

    public static long getLastMetDuration() {
        return lastMetDuration;
    }

    private void checkOverdue() {
        long periodGap = getPeriodGap();
        if (periodGap > lastMetDuration) {
            this.isOverdue = true;
        }
    }

    /**
     *
     * @return The status if a new meetup with client is overdue.
     */
    public boolean getIsOverdue() {
        return this.isOverdue;
    }

    /**
     *
     * @return The number of days since lastMet with client.
     */
    public long getPeriodGap() {
        return ChronoUnit.DAYS.between(this.lastMetDate, LocalDate.now());
    }

    public LocalDate getLastMet() {
        return this.lastMetDate;
    }

    /**
     *
     * @return A string representation of LastMet for the GUI.
     */
    public String showLastMet() {
        return "Last Met: " + this.lastMetDate + ", " + abs(this.getPeriodGap()) + " Days Ago.";
    }

    @Override
    public String toString() {
        return DateUtil.parseDateToString(this.lastMetDate);
    }
}