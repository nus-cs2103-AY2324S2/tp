package vitalconnect.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vitalconnect.model.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointment {
    public static final Appointment APPOINTMENT_1 = new Appointment("Alice Pauline", "S1234567D",
            LocalDateTime.of(2023, 3, 14, 15, 30));
    public static final Appointment APPOINTMENT_2 = new Appointment("Benson Meier", "S1234568B",
            LocalDateTime.of(2023, 3, 15, 10, 0));
    public static final Appointment APPOINTMENT_3 = new Appointment("Carl Kurz", "T1234567J",
            LocalDateTime.of(2023, 4, 16, 9, 0));
    public static final Appointment APPOINTMENT_4 = new Appointment("Daniel Meier", "F1234567N",
            LocalDateTime.of(2023, 4, 17, 14, 45));
    public static final Appointment APPOINTMENT_5 = new Appointment("Elle Meyer", "G1234567X",
            LocalDateTime.of(2023, 5, 18, 16, 30));
    public static final Appointment APPOINTMENT_6 = new Appointment("Fiona Kunz", "M1234567K",
            LocalDateTime.of(2023, 6, 19, 10, 15));
    public static final Appointment APPOINTMENT_7 = new Appointment("George Best", "F1234560R",
            LocalDateTime.of(2023, 7, 20, 11, 30));

    private TypicalAppointment() {} // prevents instantiation

    /**
     * Returns a list of typical appointments.
     */
    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4,
                APPOINTMENT_5, APPOINTMENT_6, APPOINTMENT_7));
    }
}

