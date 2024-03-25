package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientStatusTest {
    @Test
    public void equals() {
        ClientStatus clientStatus = ClientStatus.initClientStatus();

        // same object -> returns true
        assertTrue(clientStatus.equals(clientStatus));

        // same values -> returns true
        ClientStatus clientStatusCopy = ClientStatus.initClientStatus();
        assertTrue(clientStatus.equals(clientStatusCopy));

        // different types -> returns false
        assertFalse(clientStatus.equals(1));

        // null -> returns false
        assertFalse(clientStatus.equals(null));

        // different policy -> returns false
        ClientStatus differentClientStatus = ClientStatus.initNotClientStatus();
        assertFalse(clientStatus.equals(differentClientStatus));
    }
}
