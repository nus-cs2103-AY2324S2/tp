package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
public class Income {
    public static final String MESSAGE_CONSTRAINTS = "Income should at least be 0";
    private Integer incomeValue = 0;

    public Income(String incomeValue) {
        requireNonNull(incomeValue);
        this.incomeValue = Integer.parseInt(incomeValue);
    }

    public static boolean isValidIncome(String incomeValue) {
        return Integer.parseInt(incomeValue) >= 0;
    }

    @Override
    public String toString() {
        return incomeValue.toString();
    }

    public String toStringWithRepresentation() {
        return "Income is $" + incomeValue.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Income // instanceof handles nulls
                            && incomeValue.equals(((Income) other).incomeValue)); // state check
    }
}
