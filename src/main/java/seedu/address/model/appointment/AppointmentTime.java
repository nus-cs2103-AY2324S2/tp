package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an Appointment's Date, startTime and endTime in the address book.
 * ASSUMPTION: An Appointment CANNOT SPAN MULTIPLE DAYS!!!
 * Guarantees: none at the moment.
 */
public class AppointmentTime {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate appointmentDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructor for an AppointmentTime object.
     *
     * @param time Follow the format "dd/MM/yyyy [x]am-[y]pm".
     */
    public AppointmentTime(String time) {
        String dateString = time.substring(0, 10); // first 10 chars is the date
        String rawTimings = time.substring(11);
        String[] timings = rawTimings.toUpperCase().split("-"); // each time will be "AM" or "PM" now
        String startTime = timings[0].trim();
        String endTime = timings[1].trim();
        this.appointmentDate = LocalDate.parse(dateString, AppointmentTime.DATE_FORMAT);
        this.startTime = parseRawTiming(startTime);
        this.endTime = parseRawTiming(endTime);
    }

    private LocalTime parseRawTiming(String rawTime) {
        int hour = Integer.parseInt(rawTime.substring(0, rawTime.length() - 2));
        String amPm = rawTime.substring(rawTime.length() - 2).toUpperCase();

        if (amPm.equals("AM")) {
            return LocalTime.of(hour == 12 ? 0 : hour, 0);
        } else if (amPm.equals("PM")) {
            return LocalTime.of(hour == 12 ? 12 : 12 + hour, 0);
        } else {
            throw new IllegalArgumentException("Invalid time format: " + rawTime);
        }
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha").withLocale(Locale.ENGLISH);

        String dateStr = appointmentDate.format(dateFormatter);
        String startTimeStr = startTime.format(timeFormatter).toLowerCase();
        String endTimeStr = endTime.format(timeFormatter).toLowerCase();

        return dateStr + " " + startTimeStr + "-" + endTimeStr;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentTime)) {
            return false;
        }

        AppointmentTime otherAppointmentTime = (AppointmentTime) other;
        boolean sameDate = appointmentDate.equals(otherAppointmentTime.getAppointmentDate());
        boolean sameStart = startTime.equals(otherAppointmentTime.getStartTime());
        boolean sameEnd = endTime.equals(otherAppointmentTime.getEndTime());
        return (sameDate && sameStart && sameEnd);
    }

    /**
     * The string displayed on the UI.
     */
    @Override
    public String toString() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
        String formattedDate = appointmentDate.format(DATE_FORMAT);
        String formattedStartTime = startTime.format(timeFormat);
        String formattedEndTime = endTime.format(timeFormat);
        return formattedDate + ": from " + formattedStartTime + " to " + formattedEndTime;
    }
}
