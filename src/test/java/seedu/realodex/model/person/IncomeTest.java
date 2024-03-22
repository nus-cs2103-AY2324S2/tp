package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncome_throwsIllegalArgumentException() {
        String invalidIncome = "";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncome));

        String invalidIncomeWithSpaces = " ";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncomeWithSpaces));
    }

    @Test
    public void isValidIncome() {
        // null income
        assertThrows(NullPointerException.class, () -> Income.isValidIncome(null));

        // invalid income
        assertFalse(Income.isValidIncome("-1")); // less than 0
        assertFalse(Income.isValidIncome("-3")); // less than 0
        assertFalse(Income.isValidIncome("-121333213213131313")); // less than 0 but very negative


        // valid income numbers
        assertTrue(Income.isValidIncome("2")); // more than 1
        assertTrue(Income.isValidIncome("93121"));
        assertTrue(Income.isValidIncome("1242938231332131313")); // long number
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

        // different types (integer vs float) -> returns false
        assertFalse(income.equals(5.0f));

        // different types (integer vs word) -> returns false
        assertFalse(income.equals("imaword!"));

        // different values -> returns false
        assertFalse(income.equals(new Income("995")));
    }
}
