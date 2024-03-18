package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Order}'s index matches the input.
 */
public class MatchingOrderIndexPredicate implements Predicate<Order> {
    private final List<String> inputs;

    public MatchingOrderIndexPredicate(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<String> getInput() {
        return this.inputs;
    }

    @Override
    public boolean test(Order order) {
        return inputs.stream()
                .anyMatch(input -> StringUtil.containsWordIgnoreCase(Integer.toString(order.getId()), input));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchingOrderIndexPredicate)) {
            return false;
        }

        MatchingOrderIndexPredicate otherMatchingOrderIndexPredicate = (MatchingOrderIndexPredicate) other;
        return inputs.equals(otherMatchingOrderIndexPredicate.inputs);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("inputs", this.inputs).toString();
    }
}
