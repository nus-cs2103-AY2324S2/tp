package seedu.address.model.person;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.model.person.exceptions.PolicyNotFoundException;
import seedu.address.model.policy.Policy;


/**
 * The type Policy list.
 */
public class PolicyList {
    /**
     * The Policy list.
     */
    public final ArrayList<Policy> policyList;

    /**
     * Instantiates a new Policy list.
     */
    public PolicyList() {
        this.policyList = new ArrayList<Policy>();
    }

    /**
     * Instantiates a new Policy list.
     *
     * @param policyList the policy list
     */
    public PolicyList(ArrayList<Policy> policyList) {
        this.policyList = policyList;
    }

    /**
     * Add policy.
     *
     * @param newPolicy the new policy
     */
    public void addPolicy(Policy newPolicy) {
        policyList.add(newPolicy);
    }

    /**
     * Delete policy.
     *
     * @param policyId the policy id
     * @throws PolicyNotFoundException the policy not found exception
     */
    public void deletePolicy(String policyId) throws PolicyNotFoundException {
        Policy toDelete = findPolicy(policyId);
        policyList.remove(toDelete);
    }
    private Policy findPolicy(String policyId) throws PolicyNotFoundException {
        for (Policy p: policyList) {
            if (p.isID(policyId)) {
                return p;
            }
        }
        throw new PolicyNotFoundException();
    }

    /**
     * Has policy id boolean.
     *
     * @param newPolicy the new policy
     * @return the boolean
     */
    public boolean hasPolicyID(Policy newPolicy) {
        boolean isConflicting = false;
        for (Policy p: policyList) {
            if (p.hasSameID(newPolicy)) {
                isConflicting = true;
                break;
            }
        }
        return isConflicting;
    }

    /**
     * To stream stream.
     *
     * @return the stream
     */
    public Stream<Policy> toStream() {
        return policyList.stream();
    }

    /**
     * Gets policy list clone.
     *
     * @return the policy list clone
     */
    public PolicyList getPolicyListClone() {
        @SuppressWarnings("unchecked")
        // cloneArraylist is a clone of policyList and can be safely typecasted
        ArrayList<Policy> cloneArraylist = (ArrayList<Policy>) policyList.clone();
        return new PolicyList(cloneArraylist);
    }

    public String toString() {
        return policyList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof PolicyList)) {
            return false;
        }

        PolicyList otherPolicyList = (PolicyList) other;
        return policyList.equals(otherPolicyList.policyList);
    }
}
