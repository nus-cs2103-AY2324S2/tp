package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.PolicyList;

public class PolicyListTest {
    private static final Policy policy1 = new Policy("Life", "1");
    private static final Policy policy2 = new Policy("Health", "2");
    private static final Policy policy3 = new Policy("Travel", "3");
    private static final Policy policy4 = new Policy("Saving", "4");

    @Test
    public void addPolicy() {
        ArrayList<Policy> policyArrayList = new ArrayList<>();
        policyArrayList.add(policy1);
        policyArrayList.add(policy2);
        policyArrayList.add(policy3);
        ArrayList<Policy> differentArrayList = (ArrayList<Policy>) policyArrayList.clone();
        PolicyList actualPolicyList = new PolicyList(policyArrayList);
        actualPolicyList.addPolicy(policy4);
        differentArrayList.add(policy4);
        assertEquals(actualPolicyList.policyList, differentArrayList);
    }

    @Test
    public void hasPolicyID() {
        ArrayList<Policy> policyArrayList = new ArrayList<>();
        policyArrayList.add(policy1);
        policyArrayList.add(policy2);
        policyArrayList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyArrayList);

        assertTrue(actualPolicyList.hasPolicyID(policy2));
        assertFalse(actualPolicyList.hasPolicyID(policy4));
    }

    @Test
    public void getPolicyListClone() {
        ArrayList<Policy> policyArrayList = new ArrayList<>();
        policyArrayList.add(policy1);
        policyArrayList.add(policy2);
        policyArrayList.add(policy3);
        PolicyList actualPolicyList = new PolicyList(policyArrayList);
        assertEquals(actualPolicyList, actualPolicyList.getPolicyListClone());
    }

    @Test
    public void equals() {
        ArrayList<Policy> policyArrayList = new ArrayList<>();
        policyArrayList.add(policy1);
        policyArrayList.add(policy2);
        policyArrayList.add(policy3);
        PolicyList policyList = new PolicyList(policyArrayList);
        PolicyList copyPolicyList = new PolicyList((ArrayList<Policy>) policyArrayList.clone());
        ArrayList<Policy> differentArrayList = (ArrayList<Policy>) policyArrayList.clone();
        differentArrayList.add(policy4);
        PolicyList differentPolicyList = new PolicyList(differentArrayList);

        assertTrue(policyList.equals(policyList));
        assertTrue(policyList.equals(copyPolicyList));
        assertFalse(policyList.equals(null));
        assertFalse(policyList.equals(5));
        assertFalse(policyList.equals(differentPolicyList));
    }

}
