package seedu.address.model.student.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code student}'s {@code Bolt} is within the bounds specified.
 */
public class BoltWithinBoundsPredicate implements Predicate<Student> {
    private final String operator;
    private final Integer value;

    /**
     * Initialises the operator and value that defines the bound.
     *
     * @param operator
     * @param value
     */
    public BoltWithinBoundsPredicate(String operator, Integer value) {
        this.operator = operator;
        this.value = value;
    }

    /**
     * Checks whether the number of bolts is within the specified bounds.
     *
     * @param student the input argument
     * @return
     */
    @Override
    public boolean test(Student student) {
        switch (operator) {
        case "<":
            return student.getBolt().numOfBolts < value;
        case "<=":
            return student.getBolt().numOfBolts <= value;
        case ">":
            return student.getBolt().numOfBolts > value;
        case ">=":
            return student.getBolt().numOfBolts >= value;
        case "=":
            return student.getBolt().numOfBolts.equals(value);
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BoltWithinBoundsPredicate)) {
            return false;
        }

        BoltWithinBoundsPredicate otherBoltWithinBoundsPredicate = (BoltWithinBoundsPredicate) other;
        return value.equals(otherBoltWithinBoundsPredicate.value)
                && operator.equals(otherBoltWithinBoundsPredicate.operator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("value", value).toString();
    }
}
