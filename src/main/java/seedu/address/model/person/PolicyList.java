package seedu.address.model.person;

import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.policy.Policy;


/**
 * The type Policy list.
 */
public class PolicyList {
    /**
     * The Policy list.
     */
    public final ObservableList<Policy> policyList;

    /**
     * Instantiates a new Policy list.
     */
    public PolicyList() {
        this.policyList = FXCollections.observableArrayList();
    }

    /**
     * Instantiates a new Policy list.
     *
     * @param policyList the policy list
     */
    public PolicyList(ObservableList<Policy> policyList) {
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
     */
    public void deletePolicy(String policyId) {
        Policy toDelete = findPolicy(policyId);
        policyList.remove(toDelete);
    }

    private Policy findPolicy(String policyId) {
        for (Policy p: policyList) {
            if (p.isID(policyId)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Has conflicting policy id boolean.
     *
     * @param newPolicy the new policy
     * @return the boolean
     */
    public boolean hasConflictingPolicyId(Policy newPolicy) {
        assert newPolicy != null;
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
     * Has policy boolean.
     *
     * @param policyId the policy id
     * @return the boolean
     */
    public boolean hasPolicy(String policyId) {
        return findPolicy(policyId) != null;
    }

    /**
     * Creates a stream from PolicyList.
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
        ObservableList<Policy> cloneArraylist = FXCollections.observableArrayList(policyList);
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
