package seedu.address.model.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ATTENDED_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ATTENDED_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class AppointmentListTest {

    private final AppointmentList appointmentList = new AppointmentList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), appointmentList.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AppointmentList newData = getTypicalAppointmentList();
        appointmentList.resetData(newData);
        assertEquals(newData, appointmentList);
    }

    //TODO: add duplicate appointments check

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.hasAppointment(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(appointmentList.hasAppointment(ATTENDED_FIRST_APPOINTMENT));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        appointmentList.addAppointment(ATTENDED_FIRST_APPOINTMENT);
        assertTrue(appointmentList.hasAppointment(ATTENDED_FIRST_APPOINTMENT));
    }

}
