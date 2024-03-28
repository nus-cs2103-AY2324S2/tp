package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.policy.Policy;

public class PolicyListTest {
    private static final Policy policy1 = new Policy("Life", "1");
    private static final Policy policy2 = new Policy("Health", "2");
    private static final Policy policy3 = new Policy("Travel", "3");
    private static final Policy policy4 = new Policy("Saving", "4");

    @Test
    public void addPolicy() {
        ObservableList<Policy> policyObservableList = FXCollections.observableArrayList();
        policyObservableList.add(policy1);
        policyObservableList.add(policy2);
        policyObservableList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyObservableList);
        ObservableList<Policy> expectedPolicyObservableList = FXCollections.observableArrayList(policyObservableList);
        expectedPolicyObservableList.add(policy4);
        PolicyList expectedPolicyList = new PolicyList(expectedPolicyObservableList);
        actualPolicyList.addPolicy(policy4);
        assertEquals(actualPolicyList, expectedPolicyList);
    }

    @Test
    public void deletePolicy() {
        ObservableList<Policy> expectedPolicyObservableList = FXCollections.observableArrayList();
        expectedPolicyObservableList.add(policy1);
        expectedPolicyObservableList.add(policy2);
        PolicyList expectedPolicyList = new PolicyList(expectedPolicyObservableList);
        ObservableList<Policy> actualPolicyObservableList = FXCollections
                .observableArrayList(expectedPolicyObservableList);
        actualPolicyObservableList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(actualPolicyObservableList);
        actualPolicyList.deletePolicy(policy3.policyId);
        assertEquals(actualPolicyList, expectedPolicyList);
    }

    @Test
    public void hasConflictingPolicyID() {
        ObservableList<Policy> policyObservableList = FXCollections.observableArrayList();
        policyObservableList.add(policy1);
        policyObservableList.add(policy2);
        policyObservableList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyObservableList);

        assertTrue(actualPolicyList.hasConflictingPolicyId(policy2));
        assertFalse(actualPolicyList.hasConflictingPolicyId(policy4));
    }

    @Test
    public void hasPolicy() {
        ObservableList<Policy> policyObservableList = FXCollections.observableArrayList();
        policyObservableList.add(policy1);
        policyObservableList.add(policy2);
        policyObservableList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyObservableList);

        assertTrue(actualPolicyList.hasPolicy(policy2.policyId));
        assertFalse(actualPolicyList.hasPolicy(policy4.policyId));
    }

    @Test
    public void getPolicyListClone() {
        ObservableList<Policy> policyObservableList = FXCollections.observableArrayList();
        policyObservableList.add(policy1);
        policyObservableList.add(policy2);
        policyObservableList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyObservableList);
        assertEquals(actualPolicyList, actualPolicyList.getPolicyListClone());
    }

    @Test
    public void equals() {
        ObservableList<Policy> policyObservableList = FXCollections.observableArrayList();
        policyObservableList.add(policy1);
        policyObservableList.add(policy2);
        policyObservableList.add(policy3);
        PolicyList policyList = new PolicyList(policyObservableList);
        PolicyList copyPolicyList = policyList.getPolicyListClone();
        PolicyList differentPolicyList = policyList.getPolicyListClone();
        differentPolicyList.addPolicy(policy4);


        assertTrue(policyList.equals(policyList));
        assertTrue(policyList.equals(copyPolicyList));
        assertFalse(policyList.equals(null));
        assertFalse(policyList.equals(5));
        assertFalse(policyList.equals(differentPolicyList));
    }

}
