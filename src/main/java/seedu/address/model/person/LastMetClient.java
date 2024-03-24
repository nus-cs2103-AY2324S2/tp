package seedu.address.model.person;


import javafx.collections.ObservableList;

/**
 * Represents a Client that is being displayed.
 */
public class LastMetClient {

    private ObservableList<Person> lastMetList;

    public LastMetClient(ObservableList<Person> lastMetList) {
        this.lastMetList = lastMetList;
    }

    public ObservableList<Person> getLastMetList() {
        return this.lastMetList;
    }

    public void setLastMetList(ObservableList<Person> lastMetList) {
        this.lastMetList = lastMetList;
    }

    public boolean hasLastMetList() {
        return this.lastMetList != null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LastMetClient)) {
            return false;
        }

        LastMetClient otherLastMetClient = (LastMetClient) other;
        if (lastMetList == null) {
            return otherLastMetClient.getLastMetList() == null;
        }
        return lastMetList.equals(otherLastMetClient.getLastMetList());
    }
}
