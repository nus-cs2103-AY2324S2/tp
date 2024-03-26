package seedu.address.model.person.relationship;

import java.util.UUID;

public class SiblingRelationship extends FamilyRelationship {
    public SiblingRelationship(UUID person1, UUID person2) {
        super(person1, person2, "sibling", "sibling");
    }
}