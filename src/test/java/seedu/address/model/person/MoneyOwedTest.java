package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.Assert;

public class MoneyOwedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new MoneyOwed(null));
    }

    @Test
    public void constructor_validMoneyString_success() {
        MoneyOwed moneyOwed = new MoneyOwed("100.00");
        assertEquals(100.00f, moneyOwed.moneyOwed);
    }

    @Test
    public void constructor_invalidMoneyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MoneyOwed("abc"));
    }

    @Test
    public void getMessage_noOwe() {
        MoneyOwed moneyOwed = new MoneyOwed("0");
        assertEquals(moneyOwed.getMessage(), MoneyOwed.NO_MONEY_OWED_MESSAGE);
    }

    @Test
    public void getMessage_userOwes() {
        MoneyOwed moneyOwed = new MoneyOwed("-12.5");
        assertEquals(moneyOwed.getMessage(), String.format(MoneyOwed.USER_OWES_MONEY_MESSAGE, "12.50"));
    }

    @Test
    public void getMessage_owesUser() {
        MoneyOwed moneyOwed = new MoneyOwed("5.40");
        assertEquals(moneyOwed.getMessage(), String.format(MoneyOwed.PERSON_OWES_MONEY_MESSAGE, "5.40"));
    }
}
