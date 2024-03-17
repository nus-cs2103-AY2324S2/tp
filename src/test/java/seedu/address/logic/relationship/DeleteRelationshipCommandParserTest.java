package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.relationship.RelationshipManager;

class DeleteRelationshipCommandParserTest {

    private Map<String, Person> personMap;
    private RelationshipManager relationshipManager;
    private DeleteRelationshipCommandParser parser;

    @BeforeEach
    void setUp() {
        personMap = new HashMap<>();
        relationshipManager = new RelationshipManager();
        parser = new DeleteRelationshipCommandParser(personMap, relationshipManager);
    }

    @Test
    void parse_validInput_success() {
        // Add some dummy persons and relationships to test parsing
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        personMap.put(person1.getUuidString(), person1);
        personMap.put(person2.getUuidString(), person2);
        relationshipManager.addRelationship("TestRelationship", null);

        // Parse a valid delete relationship command
        assertDoesNotThrow(() -> parser.parse("delete /TestRelationship "
                + person1.getUuidString() + "," + person2.getUuidString()));
    }

    @Test
    void parse_invalidMissingParts_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("delete /TestRelationship 1234"));
    }

    @Test
    void parse_invalidIncorrectUuids_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                parser.parse("delete /TestRelationship invalid,invalid"));
    }

    @Test
    void parse_invalidRelationshipType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("delete /InvalidType 1234,5678"));
    }
}
