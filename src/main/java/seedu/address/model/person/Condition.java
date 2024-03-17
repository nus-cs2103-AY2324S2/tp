package seedu.address.model.person;

/**
 * Represents a Person's condition in the address book.
 * Guarantees: immutable;
 */
public class Condition {
    private final String condition;

    public Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return this.condition;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Condition)) {
            return false;
        }

        Condition otherCondition = (Condition) other;
        return condition.equals(otherCondition.condition);
    }
}
