package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PatientList;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PatientList} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[]{
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        getTagSet("colleagues", "friends")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        getTagSet("neighbours")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        getTagSet("colleagues"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
            new Appointment(1, LocalDateTime.of(2021, 10, 10, 10, 10),
                        1, "First appointment", true, 1),
            new Appointment(2, LocalDateTime.of(2021, 10, 10, 10, 10),
                        2, "Second appointment", false, 3),
            new Appointment(3, LocalDateTime.of(2021, 10, 10, 10, 10),
                        3, "Third appointment", true, 5),
            new Appointment(4, LocalDateTime.of(2021, 10, 30, 10, 10),
                        1, "Fourth appointment", false, 4),
        };
    }

    public static ReadOnlyAppointmentList getSampleAppointmentList() {
        AppointmentList sampleAl = new AppointmentList();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAl.addAppointment(sampleAppointment);
        }
        return sampleAl;
    }

    public static ReadOnlyPatientList getSamplePatientList() {
        PatientList sampleAb = new PatientList();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
