package seedu.address.testutil;

import seedu.address.model.NetConnect;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building netconnect objects.
 * Example usage: <br>
 * {@code NetConnect ab = new NetConnectBuilder().withPerson("John", "Doe").build();}
 */
public class NetConnectBuilder {

    private final NetConnect netConnect;

    public NetConnectBuilder() {
        netConnect = new NetConnect();
    }

    public NetConnectBuilder(NetConnect netConnect) {
        this.netConnect = netConnect;
    }

    /**
     * Adds a new {@code Person} to the {@code NetConnect} that we are building.
     */
    public NetConnectBuilder withPerson(Person person) {
        netConnect.addPerson(person);
        return this;
    }

    public NetConnect build() {
        return netConnect;
    }
}
