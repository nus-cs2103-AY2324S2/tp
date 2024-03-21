package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelationshipUtilTest {
    private static final UUID PERSON_1_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID PERSON_2_UUID =  UUID.fromString("00000000-0000-0000-0000-000000000002");
    private static final UUID PERSON_3_UUID =  UUID.fromString("00000000-0000-0000-0000-000000000003");
    private static final UUID PERSON_4_UUID =  UUID.fromString("00000000-0000-0000-0000-000000000004");
    private static final String FAMILY_RELATIONSHIP_DESCRIPTOR = "family";
    private static final String FRIEND_RELATIONSHIP_DESCRIPTOR = "friend";
    private static final Relationship[] testData = new Relationship[] {
        new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR),
                new Relationship(PERSON_1_UUID, PERSON_2_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR),
                new Relationship(PERSON_3_UUID, PERSON_4_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR),
                new Relationship(PERSON_4_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR),
                new Relationship(PERSON_1_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR),
                new Relationship(PERSON_1_UUID, PERSON_4_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR)
    };
    private RelationshipUtil relationships;
    @BeforeEach
    void setUp() {
        relationships = new RelationshipUtil();
        for (Relationship r : testData) {
            relationships.addRelationship(r);
        }
    }
    @Test
    public void testConstructorDoesNotThrowException() {
        assertDoesNotThrow(() -> new RelationshipUtil());
    }
    @Test
    public void testAddRelationship() {
        Relationship toAdd = new Relationship(PERSON_2_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        assertEquals(relationships.hasRelationship(toAdd), false);
        relationships.addRelationship(toAdd);
        assertEquals(relationships.hasRelationship(toAdd), true);
    }
    @Test
    public void testDeleteRelationship() {
        Relationship toDelete = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        assertEquals(relationships.hasRelationship(toDelete), true);
        relationships.deleteRelationship(toDelete);
        assertEquals(relationships.hasRelationship(toDelete), false);
    }

    @Test
    public void testGetExistingRelationship() {
        Relationship toGet = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        String getRelationship = relationships.getExistingRelationship(toGet);
        assertEquals(toGet.toString(), getRelationship);
    }
    @Test
    public void testGetExistingRelationshipThrowsIndexOutOfBoundException() {
        Relationship toGet = new Relationship(PERSON_2_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        assertThrows(IndexOutOfBoundsException.class, () -> relationships.getExistingRelationship(toGet));
    }
    @Test
    public void testEqualsMethod() {
        RelationshipUtil test = new RelationshipUtil();
        for (int i = 0; i < testData.length; i++) {
            test.addRelationship(testData[i]);
        }
        assertEquals(test.equals(relationships), true);
    }

    @Test
    public void testEqualsSwapPairMethod() {
        RelationshipUtil test = new RelationshipUtil();
        for (int i = 0; i < testData.length - 1; i++) {
            test.addRelationship(testData[i]);
        }
        test.addRelationship(new Relationship(PERSON_4_UUID, PERSON_1_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR));
        assertEquals(test.equals(relationships), true);
    }

}
