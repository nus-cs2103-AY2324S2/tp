package seedu.address.model.person.relationship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Represents a relationship between two people.
 */
public class Relationship {
    protected static ArrayList<String> validDescriptors = new ArrayList<String>(
            Arrays.asList("family",
                    "friend"));
    protected UUID person1;
    protected UUID person2;
    protected String relationshipDescriptor;

    /**
     * Creates a new Relationship object with the given UUIDs.
     *
     * @param person1 The UUID of the first person in the relationship.
     * @param person2 The UUID of the second person in the relationship.
     */
    public Relationship(UUID person1, UUID person2, String relationshipDescriptor) {
        this.person1 = person1;
        this.person2 = person2;

        if (!validDescriptors.contains(relationshipDescriptor)) {
            throw new IllegalArgumentException("Invalid Relationship type");
        }
        this.relationshipDescriptor = relationshipDescriptor;
    }
    // Getters for person UUIDs
    public UUID getPerson1() {
        return person1;
    }

    public UUID getPerson2() {
        return person2;
    }
    public String getRoleDescriptor(UUID targetPerson) {
        return this.relationshipDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Relationship)) {
            return false;
        }
        Relationship other = (Relationship) o;
        return (other.person1.equals(this.person1) && other.person2.equals(this.person2))
                || (other.person1.equals(this.person2) && other.person2.equals(this.person1));
    }
    @Override
    public String toString() {
        return String.format("%s and %s are %s", person1.toString(),
                person2.toString(), this.relationshipDescriptor);
    }
}
