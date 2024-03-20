package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW_1;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentViewBuilder;

public class AppointmentViewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentView(null, null));
    }

    @Test
    public void isSameAppointmentView() {
        // same object -> returns true
        assertTrue(ALICE_APPT_VIEW.isSameAppointmentView(ALICE_APPT_VIEW));

        // null -> returns false
        assertFalse(ALICE_APPT_VIEW.isSameAppointmentView(null));

        // different name, all other attributes same -> returns false
        AppointmentView editedAliceApptView = new AppointmentViewBuilder(ALICE_APPT_VIEW)
            .withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_APPT_VIEW.isSameAppointmentView(editedAliceApptView));

        // different appointment, all other attributes same -> returns false
        editedAliceApptView = new AppointmentViewBuilder(ALICE_APPT_VIEW).withAppointment(BOB_APPT).build();
        assertFalse(ALICE_APPT_VIEW.isSameAppointmentView(editedAliceApptView));
    }

    @Test
    public void equals() {
        // same values -> returns true
        AppointmentView aliceApptCopyView = new AppointmentViewBuilder(ALICE_APPT_VIEW).build();
        assertTrue(ALICE_APPT_VIEW.equals(aliceApptCopyView));

        // same object -> returns true
        assertTrue(ALICE_APPT_VIEW.equals(ALICE_APPT_VIEW));

        // null -> returns false
        assertFalse(ALICE_APPT_VIEW.equals(null));

        // different type -> returns false
        assertFalse(ALICE_APPT_VIEW.equals(5));

        // different name -> returns false
        AppointmentView editedAliceApptView = new AppointmentViewBuilder(ALICE_APPT_VIEW)
                .withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_APPT_VIEW.equals(editedAliceApptView));

        // different appointment -> returns false
        editedAliceApptView = new AppointmentViewBuilder(ALICE_APPT_VIEW)
                .withAppointment(BOB_APPT).build();
        assertFalse(ALICE_APPT_VIEW.equals(editedAliceApptView));
    }

    @Test
    public void toStringMethod() {
        String expected = AppointmentView.class.getCanonicalName()
                + "{name=" + ALICE_APPT_VIEW_1.getName()
                + ", appointment=" + ALICE_APPT_VIEW_1.getAppointment() + "}";
        assertEquals(expected, ALICE_APPT_VIEW_1.toString());
    }

}
