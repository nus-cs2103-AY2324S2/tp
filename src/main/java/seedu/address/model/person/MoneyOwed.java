package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


public class MoneyOwed {
    public final Float moneyOwed;
    public static final String MESSAGE_CONSTRAINTS =
            "Money Owed should be at most 2 decimal places in the following format 'xxx.xx' or '-xxx.xx'. ";
    public static final String VALIDATION_REGEX = "^(?:-)?\\d+(\\.\\d{2})?";

    public MoneyOwed(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        moneyOwed = Float.parseFloat(money);
    }
    public static boolean isValidMoney(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static void main (String[] args) {
        System.out.println(isValidMoney("100.10"));
        System.out.println(isValidMoney("100.100"));
        System.out.println(isValidMoney("100"));
        System.out.println(isValidMoney("-000493095.54"));
        System.out.println(Double.parseDouble("-000.10"));

    }
    @Override
    public String toString() {
        return moneyOwed.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MoneyOwed)) {
            return false;
        }

        MoneyOwed otherName = (MoneyOwed) other;
        return moneyOwed.equals(otherName.moneyOwed);
    }

    @Override
    public int hashCode() {
        return moneyOwed.hashCode();
    }
}
