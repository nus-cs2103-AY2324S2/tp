package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "20/hr";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null Salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid Salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("50"));
        assertFalse(Salary.isValidSalary("50/hr"));
        assertFalse(Salary.isValidSalary("$50"));

        // valid Salary
        assertTrue(Salary.isValidSalary("$500/hr")); // exactly 3 numbers
        assertTrue(Salary.isValidSalary("$50/hr"));
    }

    @Test
    public void equals() {
        Salary salary = new Salary("$50/hr");

        // same values -> returns true
        assertTrue(salary.equals(new Salary("$50/hr")));

        // same object -> returns true
        assertTrue(salary.equals(salary));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals("$50/hr"));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("$60/hr")));
    }
}
