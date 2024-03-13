package seedu.address.model.person;

import static java.lang.Math.abs;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import seedu.address.commons.util.DateUtil;

/**
 * Represents the last met date with a Client in the address book.
 */
public class LastMet {
    private static long lastMetDuration = 90;
    private final LocalDate lastMetDate;
    private boolean isOverdue;

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
        } else {
            this.isOverdue = false;
        }
    }

    /**
     * Method returns isOverdue boolean
     * @return The status if a new meetup with client is overdue.
     */
    public boolean getIsOverdue() {
        return this.isOverdue;
    }

    /**
     * Method returns number of days since lastMet with client.
     * @return The number of days since lastMet with client.
     */
    public long getPeriodGap() {
        return ChronoUnit.DAYS.between(this.lastMetDate, LocalDate.now());
    }

    public LocalDate getLastMet() {
        return this.lastMetDate;
    }

    /**
     * Method returns a string representation of LastMet for the GUI.
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
