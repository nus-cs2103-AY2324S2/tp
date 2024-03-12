package seedu.address.model.person;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Schedule class is responsible for keep tracking for appointments with a Client in the address book.
 */
public class Schedule {
    private LocalDateTime schedule;
    private boolean isDone;
    private boolean isMissed;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedule A valid schedule dateTime.
     */
    public Schedule(LocalDateTime schedule) {
        this.schedule = schedule;
        this.isDone = false;
        checkIsMissed();
    }

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedule A valid schedule dateTime.
     * @param isDone Status of schedule if known
     */
    public Schedule(LocalDateTime schedule, boolean isDone) {
        this.schedule = schedule;
        this.isDone = isDone;
        checkIsMissed();
    }

    public LocalDateTime getSchedule() {
        return this.schedule;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markIsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return DateTimeUtil.parseDateToString(this.schedule) + "/" + isDoneToString();
    }

    /**
     * Method is used for generating a String representation for the GUI.
     * @return A string representation of Schedule for the GUI.
     */
    public String showSchedule() {
        if (isDone) {
            return "No upcoming appointment";
        } else {
            return "Your next appointment is " + DateTimeUtil.parseDateToString(this.schedule);
        }
    }

    /**
     * Method is used for converting Storage values to boolean isDone when loading app.
     * @param input
     * @return
     */
    public static boolean checkIsDoneFromString(String input) {
        if (input.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method is used for converting boolean isDone value to String value for Storage.
     * @return
     */
    public String isDoneToString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    private void checkIsMissed() {
        if (this.schedule.isAfter(LocalDateTime.now())) {
            this.isMissed = true;
        } else {
            this.isMissed = false;
        }
    }

    public boolean getIsMissed() {
        return this.isMissed;
    }
}
