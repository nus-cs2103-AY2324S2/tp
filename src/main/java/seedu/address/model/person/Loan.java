package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;

/**
 * Represents a Loan in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Loan {

    public static final String MESSAGE_CONSTRAINTS = "Loans must be positive and have "
                                                    + "a start date before the end date.";


    private final int id;
    private final float amount;
    private final Date startDate;
    private final Date endDate;

    /**
     * Constructs a {@code Loan} with a given id.
     *
     * @param id A valid id.
     * @param amount A valid amount.
     * @param startDate A valid start date.
     * @param endDate A valid end date.
     */
    public Loan(int id, float amount, Date startDate, Date endDate) {
        requireAllNonNull(id, amount, startDate, endDate);
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns true if a given float is a valid amount.
     */
    public static boolean isValidAmount(float amount) {
        return amount > 0;
    }

    /**
     * Returns true if a given start date and end date are valid.
     */
    public static boolean isValidDates(Date startDate, Date endDate) {
        return startDate.before(endDate);
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return String.format("$%.2f, %s, %s", amount, startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Loan)) {
            return false;
        }

        Loan otherLoan = (Loan) other;

        return id == otherLoan.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
