package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String invalidSalary = "dsadas";
        String invalidSalary2 = "42389408320478923432423";
        String invalidSalary3 = "42389408320478923432423-438247328947328974893273482394";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary2));
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary3));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salaries
        assertFalse(Salary.isValidSalary(""));
        assertFalse(Salary.isValidSalary(" "));
        assertFalse(Salary.isValidSalary("-1"));
        assertFalse(Salary.isValidSalary("-1-4000"));
        assertFalse(Salary.isValidSalary("dsadas"));
        assertFalse(Salary.isValidSalary("5000-4000-3000"));
        assertFalse(Salary.isValidSalary("100-dfadfdsfsdfds"));
        assertFalse(Salary.isValidSalary("42389408320478923432423"));

        // valid salaries
        assertTrue(Salary.isValidSalary("0"));
        assertTrue(Salary.isValidSalary("1"));
        assertTrue(Salary.isValidSalary("99999999"));
        assertTrue(Salary.isValidSalary("100-10000"));
        assertTrue(Salary.isValidSalary("9999999-10000"));
    }

    @Test
    public void parseSalary() {
        // Test with a salary range where the first number is larger than the second
        Salary salary = new Salary("5000-4000");
        assertEquals(4000, salary.getSalary1());
        assertEquals(5000, salary.getSalary2());
        assertTrue(salary.isRange());

        // Test with a salary range where the first number is smaller than the second
        salary = new Salary("3000-4000");
        assertEquals(3000, salary.getSalary1());
        assertEquals(4000, salary.getSalary2());
        assertTrue(salary.isRange());

        // Test with a single salary
        salary = new Salary("6000");
        assertEquals(6000, salary.getSalary1());
        assertEquals(6000, salary.getSalary2());
        assertFalse(salary.isRange());
    }

    @Test
    public void toStringTest() {
        // Test with a salary range
        Salary salary = new Salary("4000-5000");
        assertEquals("4000-5000", salary.toString());

        // Test with a single salary
        salary = new Salary("6000");
        assertEquals("6000", salary.toString());
    }
}
