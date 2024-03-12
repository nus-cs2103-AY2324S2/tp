package seedu.address.model.person;

import seedu.address.commons.util.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Schedule class is responsible for keep tracking for appointments with a Client in the address book.
 */
public class Schedule {
    private LocalDateTime schedule;
    private boolean isDone;
    private boolean isMissed = false;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedule A valid schedule dateTime.
     */
    public Schedule(LocalDateTime schedule) {
        this.schedule = schedule;
        this.isDone = false;
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
    }

    public LocalDateTime getSchedule() {
        return this.schedule;
    }

    public boolean getAppointmentStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return DateTimeUtil.parseDateToString(this.schedule) + "/" + isDoneToString();
    }

    /**
     *
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
}