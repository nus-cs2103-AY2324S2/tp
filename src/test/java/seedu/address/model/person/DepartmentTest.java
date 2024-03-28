package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidDepartment = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartment));
    }

    @Test
    public void isValidDepartment() {
        // null department
        assertThrows(NullPointerException.class, () -> Department.isValidDepartment(null));

        // invalid department
        assertFalse(Department.isValidDepartment("")); // empty string
        assertFalse(Department.isValidDepartment(" ")); // spaces only

        // valid departments
        assertTrue(Department.isValidDepartment("Business Development"));
        assertTrue(Department.isValidDepartment("IT")); // one character
        assertTrue(Department.isValidDepartment("Facilities Maintenance and Sanitation")); // long department name
    }

    @Test
    public void equals() {
        Department department = new Department("Valid Department");

        // same values -> returns true
        assertTrue(department.equals(new Department("Valid Department")));

        // same object -> returns true
        assertTrue(department.equals(department));

        // null -> returns false
        assertFalse(department.equals(null));

        // different types -> returns false
        assertFalse(department.equals(5.0f));

        // different values -> returns false
        assertFalse(department.equals(new Address("Other Valid Department")));
    }
}
