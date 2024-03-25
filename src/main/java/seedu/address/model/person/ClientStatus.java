package seedu.address.model.person;

/**
 * Represents a Client's status in the address book.
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

    public ClientStatus(int status) {
        value = Status.values()[status];
    }
    public static ClientStatus initNotClientStatus() {
        return new ClientStatus(0);
    }

    public static ClientStatus initClientStatus() {
        return new ClientStatus(1);
    }

    public int getStatus() {
        return value.ordinal();
    }

    public ClientStatus increment() {
        return new ClientStatus(Math.min(this.getStatus() + 1, CLIENT_MAX));
    }

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
