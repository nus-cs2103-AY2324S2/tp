package seedu.address.model.order;

import java.util.function.Predicate;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Order}'s index matches the input.
 */
public class MatchingOrderIndexPredicate implements Predicate<Order> {
    private final int input;

    public MatchingOrderIndexPredicate(int input) {
        this.input = input;
    }

    public int getInput() {
        return this.input;
    }

    @Override
    public boolean test(Order order) {
        return order.getId() == this.input;
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
        return this.input == otherMatchingOrderIndexPredicate.input;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("input", this.input).toString();
    }
}
