package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * A UI Class that encapsulates {@code ClientDetailsCard} and {@code ClientPolicyTable}.
 * To make updating the client details and policies together easier.
 */
public class ClientViewPanel {

    private ClientDetailsCard clientDetailsCard;
    private ClientPolicyTable clientPolicyTable;

    public ClientViewPanel(Person person) {
        this.clientDetailsCard = new ClientDetailsCard(person);
        this.clientPolicyTable = new ClientPolicyTable();
    }

    public ClientDetailsCard getClientDetailsCard() {
        return clientDetailsCard;
    }

    public ClientPolicyTable getClientPolicyTable() {
        return clientPolicyTable;
    }

    /**
     * Updates the {@code ClientDetailsCard} with new {@code Person}.
     */
    public void updateClientDetailsCard(Person person) {
        this.clientDetailsCard = new ClientDetailsCard(person);
    }

    /*
    public void updateClientPolicyTable(xxx) {
        this.clientPolicyTable = newClientPolicyTable(xxx);
    }
     */

    /**
     * Updates the {@code ClientViewPanel} with new {@code Person}.
     */
    public void updateClientViewPanel(Person person) {
        updateClientDetailsCard(person);
        // updateClientPolicyTable(xxx);
    }
}
