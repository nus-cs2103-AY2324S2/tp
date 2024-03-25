package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClientStatusTest {
    @Test
    public void initNotClientStatus_returnsNotClientClientStatus() {
        assertEquals(0, ClientStatus.initNotClientStatus().getStatus());
    }

    @Test
    public void initClientStatus_returnsStartClientClientStatus() {
        assertEquals(1, ClientStatus.initClientStatus().getStatus());
    }

    @Test
    public void getStatus() {
        ClientStatus clientStatus = new ClientStatus(1);
        assertEquals(1, clientStatus.getStatus());
    }

    @Test
    public void increment() {
        ClientStatus clientStatus = ClientStatus.initClientStatus();
        assertEquals(clientStatus.increment().getStatus(), clientStatus.getStatus() + 1);

        clientStatus = new ClientStatus(3);
        assertEquals(clientStatus.increment().getStatus(), clientStatus.getStatus());
    }

    @Test
    public void decrement() {
        ClientStatus clientStatus = ClientStatus.initClientStatus();
        assertEquals(clientStatus.decrement().getStatus(), clientStatus.getStatus());

        clientStatus = new ClientStatus(3);
        assertEquals(clientStatus.decrement().getStatus(), clientStatus.getStatus() - 1);
    }

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
