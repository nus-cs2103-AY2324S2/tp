package seedu.address.model.person;


/**
 * Represents a Client that is being displayed.
 */
public class DisplayClient {

    private Person person;

    public DisplayClient(Person person) {
        this.person = person;
    }

    public Person getDisplayClient() {
        return this.person;
    }

    public void setDisplayClient(Person newPerson) {
        this.person = newPerson;
    }

    public boolean hasDisplayClient() {
        return this.person != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DisplayClient)) {
            return false;
        }

        DisplayClient otherDisplayClient = (DisplayClient) other;
        if (person == null) {
            return otherDisplayClient.getDisplayClient() == null;
        }
        return person.equals(otherDisplayClient.getDisplayClient());
    }
}
