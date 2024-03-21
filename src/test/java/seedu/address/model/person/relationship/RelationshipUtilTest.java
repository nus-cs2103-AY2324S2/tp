package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;

public class RelationshipUtilTest {

    @Test
    public void deleteRelationship_existingRelationship_successfullyDeleted() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Create RelationshipUtil instance and add the test relationship
        RelationshipUtil relationshipUtil = new RelationshipUtil();
        relationshipUtil.addRelationship(testRelationship);

        // Delete the test relationship
        relationshipUtil.deleteRelationship(testRelationship);

        // Check if the relationship has been deleted
        assertFalse(relationshipUtil.hasRelationship(testRelationship));
    }

    @Test
    public void hasRelationship_existingRelationship_returnsTrue() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Create RelationshipUtil instance and add the test relationship
        RelationshipUtil relationshipUtil = new RelationshipUtil();
        relationshipUtil.addRelationship(testRelationship);

        // Check if the relationship exists
        assertTrue(relationshipUtil.hasRelationship(testRelationship));
    }

    @Test
    public void hasRelationshipWithDescriptor_existingRelationshipWithDescriptor_returnsTrue() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Create RelationshipUtil instance and add the test relationship
        RelationshipUtil relationshipUtil = new RelationshipUtil();
        relationshipUtil.addRelationship(testRelationship);

        // Check if the relationship with the specified descriptor exists
        assertTrue(relationshipUtil.hasRelationshipWithDescriptor(testRelationship));
    }

    @Test
    public void hasRelationshipWithDescriptor_matchingRelationship_returnsTrue() {
        // Create dummy people for testing
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid1 = person1.getUuid();
        UUID uuid2 = person2.getUuid();

        // Create two test relationships
        Relationship matchingRelationship = new Relationship(uuid1, uuid2, "family");
        Relationship nonMatchingRelationship = new Relationship(uuid1, uuid2, "friend");

        // Create RelationshipUtil instance and add the relationships
        RelationshipUtil relationshipUtil = new RelationshipUtil();
        relationshipUtil.addRelationship(matchingRelationship);
        relationshipUtil.addRelationship(nonMatchingRelationship);

        // Check if the matching relationship exists with the correct descriptor
        assertTrue(relationshipUtil.hasRelationshipWithDescriptor(matchingRelationship));
    }

    @Test
    public void hasRelationshipWithDescriptor_existingRelationshipWithReversedPersons_returnsTrue() {
        // Create dummy people for testing
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid1 = person1.getUuid();
        UUID uuid2 = person2.getUuid();

        // Create a test relationship with reversed persons
        Relationship testRelationship = new Relationship(uuid2, uuid1, "family");

        // Create RelationshipUtil instance and add the test relationship
        RelationshipUtil relationshipUtil = new RelationshipUtil();
        relationshipUtil.addRelationship(new Relationship(uuid1, uuid2, "family"));

        // Check if the relationship exists with reversed persons but the same role descriptor
        assertTrue(relationshipUtil.hasRelationshipWithDescriptor(testRelationship));
    }

    @Test
    public void getExistingRelationship_existingRelationship_returnsStringRepresentation() {
        // Create a RelationshipUtil instance
        RelationshipUtil relationshipUtil = new RelationshipUtil();

        // Create UUIDs for two persons
        UUID person1Uuid = UUID.randomUUID();
        UUID person2Uuid = UUID.randomUUID();

        // Create a relationship and add it to the RelationshipUtil
        Relationship relationship = new Relationship(person1Uuid, person2Uuid, "family");
        relationshipUtil.addRelationship(relationship);

        // Retrieve the string representation using getExistingRelationship
        String expectedString = String.format("%s and %s are %s",
                person1Uuid, person2Uuid, "family");
        String actualString = relationshipUtil.getExistingRelationship(relationship);

        // Assert that the retrieved string matches the expected string representation
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getExistingRelationship_nonExistingRelationship_throwsIllegalArgumentException() {
        // Create a RelationshipUtil instance
        RelationshipUtil relationshipUtil = new RelationshipUtil();

        // Create UUIDs for two persons
        UUID person1Uuid = UUID.randomUUID();
        UUID person2Uuid = UUID.randomUUID();

        // Create a relationship without adding it to the RelationshipUtil
        Relationship relationship = new Relationship(person1Uuid, person2Uuid, "family");

        // Assert that trying to retrieve a non-existing relationship throws an exception
        assertThrows(IllegalArgumentException.class, () -> relationshipUtil.getExistingRelationship(relationship));
    }
}

// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// import java.util.UUID;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class RelationshipUtilTest {
//     private RelationshipUtil relationships;
//     @BeforeEach
//     void setUp() {
//         relationships = new RelationshipUtil();
//         for (Relationship r : testData) {
//             relationships.addRelationship(r);
//         }
//     }
//     @Test
//     public void testConstructorDoesNotThrowException() {
//         assertDoesNotThrow(() -> new RelationshipUtil());
//     }
//     @Test
//     public void testAddRelationship() {
//         Relationship toAdd = new Relationship(PERSON_2_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
//         assertEquals(relationships.hasRelationship(toAdd), false);
//         relationships.addRelationship(toAdd);
//         assertEquals(relationships.hasRelationship(toAdd), true);
//     }
//     @Test
//     public void testDeleteRelationship() {
//         Relationship toDelete = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
//         assertEquals(relationships.hasRelationship(toDelete), true);
//         relationships.deleteRelationship(toDelete);
//         assertEquals(relationships.hasRelationship(toDelete), false);
//     }

//     @Test
//     public void testGetExistingRelationship() {
//         Relationship toGet = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
//         String getRelationship = relationships.getExistingRelationship(toGet);
//         assertEquals(toGet.toString(), getRelationship);
//     }
//     @Test
//     public void testGetExistingRelationshipThrowsIndexOutOfBoundException() {
//         Relationship toGet = new Relationship(PERSON_2_UUID, PERSON_3_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
//         assertThrows(IndexOutOfBoundsException.class, () -> relationships.getExistingRelationship(toGet));
//     }
//     @Test
//     public void testEqualsMethod() {
//         RelationshipUtil test = new RelationshipUtil();
//         for (int i = 0; i < testData.length; i++) {
//             test.addRelationship(testData[i]);
//         }
//         assertEquals(test.equals(relationships), true);
//     }

//     @Test
//     public void testEqualsSwapPairMethod() {
//         RelationshipUtil test = new RelationshipUtil();
//         for (int i = 0; i < testData.length - 1; i++) {
//             test.addRelationship(testData[i]);
//         }
//         test.addRelationship(new Relationship(PERSON_4_UUID, PERSON_1_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR));
//         assertEquals(test.equals(relationships), true);
//     }
