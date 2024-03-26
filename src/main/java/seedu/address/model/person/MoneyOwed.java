package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents a Person's money owed in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(String)}
 */
public class MoneyOwed {

    public static final String MESSAGE_CONSTRAINTS =
            "Money Owed should be at most 2 decimal places in the following format 'xxx.xx' or '-xxx.xx'. ";
    public static final String VALIDATION_REGEX = "^(?:-)?\\d+(\\.\\d{0,2})?";

    public static final String NO_MONEY_OWED_MESSAGE = "You don't owe each other anything";
    public static final String USER_OWES_MONEY_MESSAGE = "You owe $%s";
    public static final String PERSON_OWES_MONEY_MESSAGE = "Owes you $%s";

    /**
     * This comparator will sort contacts with no money owed to the back.
     * Contacts that the user owes the most money to will be put first.
     * Contacts who owes the most money will be put right after contacts that
     * the user owes money to.
     */
    public static final Comparator<Person> MONEY_COMPARATOR = (personA, personB) -> {
        // If user owes personA money means personA.getMoneyOwed().moneyOwed < 0. So sort in asc order.
        if (personA.getMoneyOwed().userOwesMoney()) {
            return Float.compare(personA.getMoneyOwed().moneyOwed, personB.getMoneyOwed().moneyOwed);
        }
        // personB moneyOwed < 0 but personA moneyOwed >= 0. Put personB before personA.
        if (personB.getMoneyOwed().userOwesMoney()) {
            return 1;
        }
        // Both personA and personB >= 0. Put the larger one first.
        return Float.compare(personB.getMoneyOwed().moneyOwed, personA.getMoneyOwed().moneyOwed);
    };

    public final Float moneyOwed;

    /**
     * Constructs a {@code MoneyOwed}.
     *
     * @param money A valid amount of money owed.
     */
    public MoneyOwed(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        moneyOwed = Float.parseFloat(money);
    }

    /**
     * Returns true if a given string is a valid money amount.
     */
    public static boolean isValidMoney(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a moneyOwed is negative.
     */
    public boolean userOwesMoney() {
        return (moneyOwed < 0);
    }

    /**
     * Returns message to display on UI in String.
     */
    public String getMessage() {
        if (moneyOwed == 0) {
            return NO_MONEY_OWED_MESSAGE;
        }
        if (userOwesMoney()) {
            return String.format(USER_OWES_MONEY_MESSAGE, toString().substring(1));
        } else {
            return String.format(PERSON_OWES_MONEY_MESSAGE, this);
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f", moneyOwed);
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
