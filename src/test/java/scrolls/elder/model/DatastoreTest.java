package scrolls.elder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.TypicalLogs;
import scrolls.elder.testutil.TypicalPersons;

public class DatastoreTest {

    @Test
    public void equalsMethod() {
        Datastore datastore1 = new Datastore();
        datastore1.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        datastore1.getMutableLogStore().resetData(TypicalLogs.getTypicalLogStore());

        Datastore datastore2 = new Datastore();
        datastore2.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        datastore2.getMutableLogStore().resetData(TypicalLogs.getTypicalLogStore());

        // same values -> returns true
        assertEquals(datastore1, datastore2);

        // same object -> returns true
        assertEquals(datastore1, datastore1);

        // null -> returns false
        assertNotEquals(datastore1, null);

        // different types -> returns false
        assertNotEquals(datastore1, new Object());

        // different datastore -> returns false
        Datastore differentDatastore = new Datastore();
        differentDatastore.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        assertNotEquals(datastore1, differentDatastore);
    }

    @Test
    public void hashCodeMethod() {
        Datastore datastore1 = new Datastore();
        datastore1.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        datastore1.getMutableLogStore().resetData(TypicalLogs.getTypicalLogStore());

        Datastore datastore2 = new Datastore();
        datastore2.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        datastore2.getMutableLogStore().resetData(TypicalLogs.getTypicalLogStore());

        int hashCode1 = datastore1.hashCode();
        int hashCode2 = datastore1.hashCode();
        int hashCode3 = datastore2.hashCode();

        // Consistency: hashCode on same object returns same result
        assertEquals(hashCode1, hashCode2);

        // Equality: equal objects return same hashCode
        assertEquals(hashCode1, hashCode3);
    }
}
