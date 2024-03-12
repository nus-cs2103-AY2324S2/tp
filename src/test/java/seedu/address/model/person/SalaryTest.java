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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidSalary = "dsadas";
        String invalidSalary2 = "42389408320478923432423";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary2));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salaries
        assertFalse(Salary.isValidSalary(""));
        assertFalse(Salary.isValidSalary(" "));
        assertFalse(Salary.isValidSalary("dsadas"));
        assertFalse(Salary.isValidSalary("100-dfadfdsfsdfds"));
        assertFalse(Salary.isValidSalary("42389408320478923432423"));

        // valid salaries
        assertTrue(Salary.isValidSalary("0"));
        assertTrue(Salary.isValidSalary("1"));
        assertTrue(Salary.isValidSalary("99999999"));
        assertTrue(Salary.isValidSalary("99999999-1"));
    }
}
