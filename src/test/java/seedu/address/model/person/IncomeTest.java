package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncome_throwsNumberFormatException() {
        String invalidIncome = "";
        assertThrows(NumberFormatException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void constructor_invalidIncome_throwsNumberFormatException2() {
        String invalidIncome = " ";
        assertThrows(NumberFormatException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void constructor_invalidIncome_throwsNumberFormatException3() {
        String invalidIncome = "income"; //alphabets
        assertThrows(NumberFormatException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void constructor_invalidIncome_throwsNumberFormatException4() {
        String invalidIncome = "9011p041"; //alphabet within digits
        assertThrows(NumberFormatException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void constructor_invalidIncome_throwsNumberFormatException5() {
        String invalidIncome = "9312 1534"; // spaces within digits
        assertThrows(NumberFormatException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void isValidIncome() {
        // null income
        assertThrows(NumberFormatException.class, () -> Income.isValidIncome(null));

        // invalid income
        assertFalse(Income.isValidIncome("-1")); // less than 0

        // valid income numbers
        assertTrue(Income.isValidIncome("2")); // more than 1
        assertTrue(Income.isValidIncome("93121"));
        assertTrue(Income.isValidIncome("1242938")); // long number
    }

    @Test
    public void equals() {
        Income income = new Income("999");

        // same values -> returns true
        assertTrue(income.equals(new Income("999")));

        // same object -> returns true
        assertTrue(income.equals(income));

        // null -> returns false
        assertFalse(income.equals(null));

        // different types -> returns false
        assertFalse(income.equals(5.0f));

        // different values -> returns false
        assertFalse(income.equals(new Income("995")));
    }
}
