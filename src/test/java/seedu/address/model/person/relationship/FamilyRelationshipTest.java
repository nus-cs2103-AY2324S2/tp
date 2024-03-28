package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class FamilyRelationshipTest {
    @Test
    public void getRelationshipType_validRelationshipType_returnCorrectType() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        String relationshipType = "siblings";
        String role1 = "sibling";
        String role2 = "sibling";
    }
}
