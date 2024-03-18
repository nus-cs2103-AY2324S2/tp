package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.model.module.TutorialClass.isValidTutorialClass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

public class TutorialClassTest {

    public static final String VALID_TUTORIAL = "T09";
    public static final String INVALID_TUTORIAL = "T09#0";
    @Test
    public void equals() {
        TutorialClass tutorialClass = new TutorialClass("T09");

        // same object -> returns true
        assertTrue(tutorialClass.equals(tutorialClass));

        // same values -> returns true
        TutorialClass remarkCopy = new TutorialClass(tutorialClass.value);
        assertTrue(tutorialClass.equals(remarkCopy));

        // different types -> returns false
        assertFalse(tutorialClass.equals(1));

        // null -> returns false
        assertFalse(tutorialClass.equals(null));

        // different remark -> returns false
        TutorialClass differentModule = new TutorialClass("T01");
        assertFalse(tutorialClass.equals(differentModule));
    }

    @Test
    void isValidTutorialClass_success() {
        assertTrue(isValidTutorialClass(VALID_TUTORIAL));
    }

    @Test
    void isValidTutorialClass_failure() {
        assertFalse(isValidTutorialClass(INVALID_TUTORIAL));
    }

    @Test
    void testToString_success() {
        TutorialClass tutorial = new TutorialClass(VALID_TUTORIAL);
        assertEquals(tutorial.toString(), VALID_TUTORIAL);
    }

    @Test
    void testEmptyConstructor() {
        TutorialClass tutorialClass = new TutorialClass();
        assertEquals("", tutorialClass.value);
        try {
            Field field = TutorialClass.class.getDeclaredField("students");
            field.setAccessible(true); // Allow access to private field
            Object value = field.get(tutorialClass); // Get the value of the field
            assertTrue(((ArrayList<?>) value).isEmpty()); // Check if it's empty
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception thrown while accessing private field: " + e.getMessage());
        }
    }

    @Test
    void testConstructorWithStudents() {
        ArrayList<Person> students = new ArrayList<>();

        // Creating Person objects
        Name name1 = new Name("John");
        Email email1 = new Email("john@example.com");
        StudentId stuId1 = new StudentId("A0213333J");
        ModuleCode module1 = new ModuleCode("CS2103");
        TutorialClass tutorial1 = new TutorialClass("T01");
        Set<Tag> tags1 = new HashSet<>();
        Person student1 = new Person(name1, email1, stuId1, module1, tutorial1, tags1);

        Name name2 = new Name("Alice");
        Email email2 = new Email("alice@example.com");
        StudentId stuId2 = new StudentId("A0145678A");
        ModuleCode module2 = new ModuleCode("CS2101");
        TutorialClass tutorial2 = new TutorialClass("T02");
        Set<Tag> tags2 = new HashSet<>();
        Person student2 = new Person(name2, email2, stuId2, module2, tutorial2, tags2);

        students.add(student1);
        students.add(student2);

        TutorialClass tutorialClass = new TutorialClass(VALID_TUTORIAL, students);
        assertEquals(VALID_TUTORIAL, tutorialClass.value);
        assertEquals(2, tutorialClass.getStudents().size()); // Accessing students via getter
        assertTrue(tutorialClass.getStudents().contains(student1));
        assertTrue(tutorialClass.getStudents().contains(student2));
    }
}
