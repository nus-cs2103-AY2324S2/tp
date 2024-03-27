package scrolls.elder.model;

/**
 * Unmodifiable view of the entire datastore.
 */
public interface ReadOnlyDatastore {
    /**
     * Returns an unmodifiable view of the persons store.
     * This list will not contain any duplicate persons.
     */
    ReadOnlyPersonStore getPersonStore();

    /**
     * Returns an unmodifiable view of the logs store.
     */
    ReadOnlyLogStore getLogStore();
}
