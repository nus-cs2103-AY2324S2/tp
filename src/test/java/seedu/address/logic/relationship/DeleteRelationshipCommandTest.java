package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RelationshipManager;
import seedu.address.model.person.relationship.RoleBasedRelationship;

class DeleteRelationshipCommandTest {

    private Map<String, Person> personMap;
    private RelationshipManager relationshipManager;
    private DeleteRelationshipCommand command;

    @BeforeEach
    void setUp() {
        personMap = new HashMap<>();
        relationshipManager = new RelationshipManager();

        // Assume NameAttribute is a subclass of Attribute suitable for testing
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        String uuid1 = person1.getUuidString();
        String uuid2 = person2.getUuidString();
        personMap.put(uuid1, person1);
        personMap.put(uuid2, person2);

        command = new DeleteRelationshipCommand(personMap, relationshipManager);
    }

    @Test
    void parseCommand_validInput_relationshipRemoved() {
        // Create a relationship between two persons
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        String uuid1 = person1.getUuidString();
        String uuid2 = person2.getUuidString();
        personMap.put(uuid1, person1);
        personMap.put(uuid2, person2);

        Relationship relationship = new RoleBasedRelationship(person1.getUuid(), person2.getUuid());

        // Add the relationship to the RelationshipManager
        relationshipManager.addRelationship("TestRelationship", relationship);

        // Parse the command to delete the relationship
        assertDoesNotThrow(() -> command.parseCommand("deleterelation /TestRelationship "
                + person1.getUuid() + "," + person2.getUuid()));

        // Check if the relationship is removed
        assertTrue(relationshipManager.getRelationships("TestRelationship").isEmpty());
    }

    @Test
    void parseCommand_invalidInput_relationshipRemoved() {
        String uuid1 = personMap.keySet().iterator().next();
        String uuid2 = personMap.keySet().iterator().next();
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("deleterelation /friends " + uuid1 + " " + uuid2));
    }

    @Test
    void parseCommand_invalidRelationshipType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("deleterelation /InvalidType 1234,5678"));
    }

    @Test
    void parseCommand_invalidMissingParts_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("deleterelation /TestRelationship 1234"));
    }

    @Test
    void parseCommand_invalidIncorrectUuids_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("deleterelation /TestRelationship invalid,invalid"));
    }

    @Test
    void parseCommand_validRelationshipCheck_relationshipRemoved() {
        // Create a relationship between two persons
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        String uuid1 = person1.getUuidString();
        String uuid2 = person2.getUuidString();
        personMap.put(uuid1, person1);
        personMap.put(uuid2, person2);

        Relationship relationship = new RoleBasedRelationship(person1.getUuid(), person2.getUuid());

        // Add the relationship to the RelationshipManager
        relationshipManager.addRelationship("TestRelationship", relationship);

        // Parse the command to delete the relationship
        assertDoesNotThrow(() -> command.parseCommand("deleterelation /TestRelationship "
                + person1.getUuid() + "," + person2.getUuid()));

        // Check if the relationship is removed
        assertTrue(relationshipManager.getRelationships("TestRelationship").isEmpty());
    }

    @Test
    void parseCommand_invalidNoMatchingRelationship_throwsIllegalArgumentException() {
        // Create a relationship between two persons
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        String uuid1 = person1.getUuidString();
        String uuid2 = person2.getUuidString();
        personMap.put(uuid1, person1);
        personMap.put(uuid2, person2);

        Relationship relationship = new RoleBasedRelationship(person1.getUuid(), person2.getUuid());

        // Add the relationship to the RelationshipManager
        relationshipManager.addRelationship("TestRelationship", relationship);

        // Try to delete a relationship that doesn't exist
        assertThrows(IllegalArgumentException.class, () -> command.parseCommand("deleterelation /TestRelationship "
                + "invalid,invalid"));
    }

    @Test
    void parseCommand_relationshipNotFound_throwsIllegalArgumentException() {
        // Create a relationship between two persons
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        String uuid1 = person1.getUuidString();
        String uuid2 = person2.getUuidString();
        personMap.put(uuid1, person1);
        personMap.put(uuid2, person2);

        // No relationship added to RelationshipManager

        // Try to delete a relationship that doesn't exist
        assertThrows(IllegalArgumentException.class, () -> command.parseCommand("deleterelation /TestRelationship "
                + person1.getUuid() + "," + person2.getUuid()));
    }
}
