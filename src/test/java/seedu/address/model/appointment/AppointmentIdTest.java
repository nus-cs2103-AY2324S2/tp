package seedu.address.model.appointment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentIdTest {

    @Test
    void testToString() {
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        AppointmentId aid = new AppointmentId();
        assertTrue(aid.equals(aid));
    }

    @Test
    public void equals_diffObject_returnsFalse() {
        AppointmentId aid = new AppointmentId();
        assertFalse(aid.equals(new AppointmentId()));
    }


    @Test
    void hashCode_returnsValidHash() {
        assertNotNull(hashCode());
    }
}
