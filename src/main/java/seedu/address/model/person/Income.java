package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
public class Income {
    public static final String MESSAGE_CONSTRAINTS = "Income should at least be 0";
    private Integer incomeValue = 0;

    public Income(Integer incomeValue) {
        requireNonNull(incomeValue);
        this.incomeValue = incomeValue;
    }

    public static boolean isValidIncome(Integer incomeValue) {
        return incomeValue >= 0;
    }
}
