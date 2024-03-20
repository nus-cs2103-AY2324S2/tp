package seedu.address.ui;

import seedu.address.model.person.Person;
import seedu.address.model.person.PolicyList;

/**
 * A UI Class that encapsulates {@code ClientDetailsCard} and {@code ClientPolicyTable}.
 * To make updating the client details and policies together easier.
 */
public class ClientViewPanel {

    private ClientDetailsCard clientDetailsCard;
    private ClientPolicyTable clientPolicyTable;

    /**
     * Creates a {@code ClientViewPanel} with the given {@code person}.
     */
    public ClientViewPanel(Person person) {
        if (person == null) {
            this.clientDetailsCard = new ClientDetailsCard();
            this.clientPolicyTable = new ClientPolicyTable();
        } else {
            this.clientDetailsCard = new ClientDetailsCard(person);
            this.clientPolicyTable = new ClientPolicyTable(person.getPolicyList());
        }
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
        clientDetailsCard = new ClientDetailsCard(person);
    }

    /**
     * Updates the {@code ClientPolicyTable} with new {@code ObservableList<Policy>}
     */
    public void updateClientPolicyTable(PolicyList policyList) {
        clientPolicyTable = new ClientPolicyTable(policyList);
    }

    /**
     * Updates the {@code ClientViewPanel} with new {@code Person}.
     */
    public void updateClientViewPanel(Person person) {
        updateClientDetailsCard(person);
        updateClientPolicyTable(person.getPolicyList());
    }
}
