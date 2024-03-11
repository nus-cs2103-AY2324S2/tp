package seedu.address.model.person.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ClientStatus {

    public enum Status {
        START,
        MIDDLE,
        END;
    }

    public final Status value;

    public ClientStatus(int status) {
        this.value = Status.values()[status];
    }

    public int getStatus() {
        return value.ordinal();
    }

    @Override
    public String toString() {
        switch (value) {
        case START:
            return "Started";
        case MIDDLE:
            return "Discussing";
        case END:
            return "Successful";
        default:
            return "";
        }
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
