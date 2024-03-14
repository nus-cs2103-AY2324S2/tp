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

class AddRelationshipCommandTest {

    private Map<String, Person> personMap;
    private AddRelationshipCommand command;
    @BeforeEach
    void setUp() {
        personMap = new HashMap<>();
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

        command = new AddRelationshipCommand(personMap);

    }

    @Test
    void execute_validInputWithRoles_success() {
        String uuid1 = personMap.keySet().iterator().next();
        String uuid2 = personMap.keySet().iterator().next();
        assertDoesNotThrow(() ->
                command.parseCommand("addrelation /bioparent /child " + uuid1 + " /parent " + uuid2));
    }

    @Test
    void execute_validInputWithoutRoles_success() {
        String uuid1 = personMap.keySet().iterator().next();
        String uuid2 = personMap.keySet().iterator().next();
        assertDoesNotThrow(() -> command.parseCommand("addrelation /friends " + uuid1 + ", " + uuid2));
    }

    @Test
    void execute_invalidInputMissingParts_throwsIllegalArgumentException() {
        String uuid1 = personMap.keySet().iterator().next();
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("addrelation /bioparent /child" + uuid1));
    }

    @Test
    void execute_invalidInputIncorrectRelationshipType_throwsIllegalArgumentException() {
        String uuid1 = personMap.keySet().iterator().next();
        String uuid2 = personMap.keySet().iterator().next();
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("addrelation /invalid " + uuid1 + ", " + uuid2));
    }

    @Test
    void execute_invalidInputIncorrectUuids_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                command.parseCommand("addrelation /bioparent /child invalid /parent invalid"));
    }
}
