package scrolls.elder.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the in-memory model of the application data.
 * The interface through which all other components access application data.
 * Does not provide mutators, as that is handled by the individual stores.
 */
public class Datastore implements ReadOnlyDatastore {
    private final PersonStore persons;
    private final LogStore logs;

    /**
     * Creates an empty Datastore.
     */
    public Datastore() {
        persons = new PersonStore();
        logs = new LogStore();
    }

    /**
     * Creates a Datastore using the data in {@code toBeCopied}
     */
    public Datastore(ReadOnlyDatastore toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// Datastore accessors

    @Override
    public ReadOnlyPersonStore getPersonStore() {
        return persons;
    }

    public PersonStore getMutablePersonStore() {
        return persons;
    }

    @Override
    public ReadOnlyLogStore getLogStore() {
        return logs;
    }

    public LogStore getMutableLogStore() {
        return logs;
    }

    /**
     * Resets the existing data of this {@code Datastore} with {@code newData}.
     */
    public void resetData(ReadOnlyDatastore newData) {
        requireNonNull(newData);
        persons.resetData(newData.getPersonStore());
        logs.resetData(newData.getLogStore());
    }

    //// Overrides

    @Override
    public String toString() {
        return String.format("Datastore{persons=%s, logs=%s}", persons, logs);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Datastore)) {
            return false;
        }

        Datastore otherDatastore = (Datastore) other;
        return persons.equals(otherDatastore.persons) && logs.equals(otherDatastore.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, logs);
    }
}
