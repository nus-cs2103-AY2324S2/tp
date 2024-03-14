package seedu.address.model.person.relationship;

import java.util.UUID;

abstract class Relationship {
    protected UUID person1;
    protected UUID person2;

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
