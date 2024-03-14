// FriendsRelationshipTest.java
package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class FriendsRelationshipTest {

    @Test
    public void testFriendsRelationshipCreation() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        FriendsRelationship friendsRelationship = new FriendsRelationship(person1, person2);
        assertEquals(person1, friendsRelationship.getPerson1());
        assertEquals(person2, friendsRelationship.getPerson2());
    }
}
