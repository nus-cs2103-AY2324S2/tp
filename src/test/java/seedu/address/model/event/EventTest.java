package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Event event = new EventBuilder().build();
        assertThrows(NullPointerException.class, () -> event.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEvent_returnsFalse() {
        Event event = new EventBuilder().build();
        assertFalse(event.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEvent_returnsTrue() {
        Event event = new EventBuilder().withPerson(ALICE).build();
        assertTrue(event.hasPerson(ALICE));
    }

    @Test
    public void addPerson_nullPerson_throwsNullPointerException() {
        Event event = new EventBuilder().build();
        assertThrows(NullPointerException.class, () -> event.addPerson(null));
    }

    @Test
    public void addPerson_personAlreadyInEvent_throwsDuplicatePersonException() {
        Event event = new EventBuilder().withPerson(ALICE).build();
        assertThrows(DuplicatePersonException.class, () -> event.addPerson(ALICE));
    }

    @Test
    public void setPerson_nullTarget_throwsNullPointerException() {
        Event event = new EventBuilder().build();
        assertThrows(NullPointerException.class, () -> event.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Event event = new EventBuilder().build();
        assertThrows(NullPointerException.class, () -> event.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetNotInEvent_throwsPersonNotFoundException() {
        Event event = new EventBuilder().build();
        assertThrows(PersonNotFoundException.class, () -> event.setPerson(ALICE, BENSON));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        Event event = new EventBuilder().withPerson(ALICE).build();
        event.setPerson(ALICE, ALICE);
        assertTrue(event.getPersonList().contains(ALICE));
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        Event event = new EventBuilder().withPerson(ALICE).build();
        event.setPerson(ALICE, BENSON);
        assertFalse(event.getPersonList().contains(ALICE));
        assertTrue(event.getPersonList().contains(BENSON));
    }

    @Test
    public void toStringMethod() {
        Event event = new EventBuilder().build();
        String expected = Event.class.getCanonicalName() + "{eventName=" + event.getEventName()
                + ", persons=" + event.getPersonList().toString() + "}";

        assertEquals(expected, event.toString());
    }
}
