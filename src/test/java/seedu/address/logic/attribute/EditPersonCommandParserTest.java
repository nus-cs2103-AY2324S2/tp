package seedu.address.logic.attribute;

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

class EditPersonCommandParserTest {

    private Map<String, Person> personMap;
    private EditPersonCommandParser parser;

    @BeforeEach
    void setUp() {
        personMap = new HashMap<>();
        // Assume NameAttribute is a subclass of Attribute suitable for testing
        Attribute name = new NameAttribute("Name", "John Doe");
        Attribute[] attributes = new Attribute[]{name};

        // Adding a dummy person for testing
        Person person = new Person(attributes);
        String uuid = person.getUuidString();
        personMap.put(uuid, person);

        parser = new EditPersonCommandParser(personMap);
    }

    @Test
    void parse_addCommand_success() {
        String uuid = personMap.keySet().iterator().next();
        String command = "cmd /add /uuid " + uuid + " attributeName attributeValue";

        assertDoesNotThrow(() -> parser.parse(command));
    }

    @Test
    void parse_deleteCommand_success() {
        String uuid = personMap.keySet().iterator().next();
        String attributeName = "Name"; // Assuming the person already has this attribute
        String command = String.format("cmd /delete /uuid %s %s", uuid, attributeName);

        assertDoesNotThrow(() -> parser.parse(command));
        // Further validation can include checking if the attribute was actually deleted
    }

    @Test
    void parse_invalidCommand_throwsIllegalArgumentException() {
        String invalidCommand = "invalid /add"; // Incorrect format

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(invalidCommand);
        });

        assertTrue(exception.getMessage().contains("Invalid command format"),
                "Expected invalid command format message");
    }

}
