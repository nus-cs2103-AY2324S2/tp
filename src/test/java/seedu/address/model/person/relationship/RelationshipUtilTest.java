package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
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
    public void getExistingRelationship_existingRelationship_returnsStringRepresentation() {
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

        // Retrieve the string representation of the existing relationship
        String stringRepresentation = relationshipUtil.getExistingRelationship(testRelationship);

        // Check if the retrieved string representation matches the expected format
        assertEquals(testRelationship.toString(), stringRepresentation);
    }

    @Test
    public void getExistingRelationship_nonExistingRelationship_throwsIllegalArgumentException() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create an instance of RelationshipUtil
        RelationshipUtil relationshipUtil = new RelationshipUtil();

        // Create a test relationship (not added to the tracker)
        Relationship nonExistingRelationship = new Relationship(uuid11, uuid22, "friend");

        // Assert that an IllegalArgumentException is thrown when trying to retrieve the non-existing relationship
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relationshipUtil.getExistingRelationship(nonExistingRelationship);
        }, "Relationship does not exist.");
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
}