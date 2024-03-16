package seedu.address.model.person.relationship;

import java.util.UUID;

/**
 * Represents a relationship between two people.
 */
public abstract class Relationship {
    protected UUID person1;
    protected UUID person2;

    /**
     * Creates a new Relationship object with the given UUIDs.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public Relationship(UUID person1, UUID person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    // Getters for person UUIDs
    public UUID getPerson1() {
        return person1;
    }

    public UUID getPerson2() {
        return person2;
    }
}
