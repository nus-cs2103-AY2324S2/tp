package scrolls.elder.testutil;

import scrolls.elder.model.Datastore;

/**
 * A utility class containing application data to be used in tests.
 */
public class TypicalDatastore {
    private TypicalDatastore() {} // prevents instantiation

    /**
     * Returns an {@code Datastore} with the typical Stores.
     */
    public static Datastore getTypicalDatastore() {
        Datastore ds = new Datastore();
        ds.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());
        return ds;
    }
}
