package vitalconnect.model.person.contactinformation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactInformationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactInformation((String) null, null, null));
    }

    @Test
    public void equals() {
        Address address = new Address("Valid Address");
        Phone phone = new Phone("12345678");
        Email email = new Email("email@e.com");
        Address differentAddress = new Address("Address");
        ContactInformation contactInformation = new ContactInformation(email, phone, address);

        // same values -> returns true
        assertTrue(contactInformation.equals(new ContactInformation(email, phone, address)));

        // same object -> returns true
        assertTrue(contactInformation.equals(contactInformation));

        // null -> returns false
        assertFalse(contactInformation.equals(null));

        // different types -> returns false
        assertFalse(contactInformation.equals(5.0f));

        // different values -> returns false
        assertFalse(contactInformation.equals(new ContactInformation(email, phone, differentAddress)));
    }
}
