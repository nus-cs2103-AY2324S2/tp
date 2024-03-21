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

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return condition == null ? alt : condition;
    }

    @Override
    public String toString() {
        return this.condition;
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
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
