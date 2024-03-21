package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.AMY_APPT;
import static seedu.address.testutil.TypicalAppointments.BENSON_APPT;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.appointment.AppointmentView;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 * Appointment NRICs match those in {@code TypicalPersons}.
 */
public class TypicalAppointmentViews {
    //set up appointmentViews
    public static final AppointmentView ALICE_APPT_VIEW = new AppointmentViewBuilder()
            .withName("Alice Pauline").withAppointment(ALICE_APPT).build();

    public static final AppointmentView ALICE_APPT_VIEW_1 = new AppointmentViewBuilder()
            .withName("Alice Pauline").withAppointment(ALICE_APPT_1).build();

    public static final AppointmentView BENSON_APPT_VIEW = new AppointmentViewBuilder()
            .withName("Benson Meier").withAppointment(BENSON_APPT).build();

    //set up appointmentViews
    public static final AppointmentView AMY_APPT_VIEW = new AppointmentViewBuilder()
            .withName(VALID_NAME_AMY).withAppointment(AMY_APPT).build();

    public static final AppointmentView BOB_APPT_VIEW = new AppointmentViewBuilder()
            .withName(VALID_NAME_BOB).withAppointment(BOB_APPT).build();


    private TypicalAppointmentViews() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical
     * persons and appointments
     */
    public static AddressBook getTypicalAddressBookWithAppointments() {
        AddressBook ab = getTypicalAddressBook();
        for (AppointmentView appointment : getTypicalAppointmentViews()) {
            ab.addAppointmentView(appointment);
        }
        return ab;
    }

    public static List<AppointmentView> getTypicalAppointmentViews() {
        return new ArrayList<>(Arrays.asList(ALICE_APPT_VIEW, ALICE_APPT_VIEW_1, BENSON_APPT_VIEW));
    }
}
