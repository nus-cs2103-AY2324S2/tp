package seedu.address.model.person;

/**
 * Represents a client's status in the address book.
 * Guarantees: immutable; is always valid
 */
public class ClientStatus {

    /**
     * Enumeration of the different status levels of a person.
     */
    public enum Status {
        NOT_CLIENT,
        START,
        MIDDLE,
        END;

        @Override
        public String toString() {
            switch (this) {
            case START:
                return "Yet to start";
            case MIDDLE:
                return "In progress";
            case END:
                return "Completed";
            default:
                return "";
            }
        }
    }

    private static final int CLIENT_MIN = 1;
    private static final int CLIENT_MAX = Status.values().length - 1;

    public final Status value;

    /**
     * Constructs a {@code ClientStatus}.
     * Only to be used when reading Json files and by other methods in the {@code ClientStatus} class.
     *
     * @param status The ordinal of the status enumeration.
     */
    public ClientStatus(int status) {
        value = Status.values()[status];
    }

    /**
     * Returns a {@code ClientStatus} for non-client persons.
     * Used only when adding a non-client person to the addressbook.
     */
    public static ClientStatus initNotClientStatus() {
        return new ClientStatus(0);
    }

    /**
     * Returns a {@code ClientStatus} for client persons.
     * Used only when adding a client person to the addressbook.
     */
    public static ClientStatus initClientStatus() {
        return new ClientStatus(1);
    }

    /**
     * Returns the ordinal of the {@code ClientStatus}'s value in the Status enumeration.
     */
    public int getStatus() {
        return value.ordinal();
    }

    /**
     * Increases the value of {@code ClientStatus} by one stage according to the Status enumeration.
     * The highest value is {@code END}.
     *
     * @return A new instance of {@code ClientStatus} that has a higher value up to the maximum value.
     */
    public ClientStatus increment() {
        return new ClientStatus(Math.min(this.getStatus() + 1, CLIENT_MAX));
    }

    /**
     * Decreases the value of {@code ClientStatus} by one stage according to the Status enumeration.
     * The lowest value is {@code START}.
     *
     * @return A new instance of {@code ClientStatus} that has a lower value up to the maximum value.
     */
    public ClientStatus decrement() {
        return new ClientStatus(Math.max(this.getStatus() - 1, CLIENT_MIN));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientStatus)) {
            return false;
        }

        ClientStatus otherClientStatus = (ClientStatus) other;
        return value.equals(otherClientStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
