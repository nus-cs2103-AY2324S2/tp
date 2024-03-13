package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Appointment's email in the address book.
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
        int length = rawTime.length();
        if (length == 4) {
            int num = Integer.parseInt(rawTime.substring(0, 2));
            if (rawTime.charAt(2) == 'A') { // AM
                return LocalTime.of(num, 0);
            } else { // PM
                return LocalTime.of(12 + num, 0);
            }
        } else {
            int num = Integer.parseInt(rawTime.substring(0, 1));
            if (rawTime.charAt(1) == 'A') { // AM
                return LocalTime.of(num, 0);
            } else { // PM
                return LocalTime.of(12 + num, 0);
            }
        }
    }

    @Override
    public String toString() {
        return "YOU";
    }
}
