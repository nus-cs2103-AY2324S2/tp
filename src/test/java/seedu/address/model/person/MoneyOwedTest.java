package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

}
